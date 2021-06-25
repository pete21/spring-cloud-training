package pl.training.cloud.payments;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.commons.money.FastMoneyMapper;
import pl.training.cloud.payments.adapters.rest.PaymentDto;
import pl.training.cloud.payments.application.GetPaymentService;
import pl.training.cloud.payments.application.PaymentIdGenerator;
import pl.training.cloud.payments.application.ProcessPaymentService;
import pl.training.cloud.payments.application.UUIDPaymentIdGenerator;
import pl.training.cloud.payments.ports.events.PaymentsEventEmitter;
import pl.training.cloud.payments.ports.persistence.PaymentsQueries;
import pl.training.cloud.payments.ports.persistence.PaymentsUpdates;
import pl.training.cloud.payments.ports.time.TimeService;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;
import pl.training.cloud.payments.ports.usecases.ProcessPaymentUseCase;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.Serializable;
import java.util.function.Supplier;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
@EnableAspectJAutoProxy
@Configuration
public class PaymentsConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.training.cloud.payments.adapters.rest"))
                .build();
    }

    @Bean
    public RedisTemplate<String, Serializable> stringSerializableRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        var template = new RedisTemplate<String, Serializable>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        var config = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public FastMoneyMapper fastMoneyMapper() {
        return Mappers.getMapper(FastMoneyMapper.class);
    }

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentIdGenerator paymentIdGenerator, PaymentsUpdates paymentsUpdates, TimeService timeService, PaymentsEventEmitter eventEmitter) {
        return new ProcessPaymentService(paymentIdGenerator, paymentsUpdates, timeService, eventEmitter);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsQueries paymentsQueries) {
        return new GetPaymentService(paymentsQueries);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public Supplier<PaymentDto> paymentsChannel() {
        return PaymentDto::new;
    }*/

}
