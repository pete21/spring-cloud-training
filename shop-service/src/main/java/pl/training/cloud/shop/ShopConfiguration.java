package pl.training.cloud.shop;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.cloud.shop.application.PlaceOrderService;
import pl.training.cloud.shop.ports.payments.PaymentsService;
import pl.training.cloud.shop.ports.usecases.PlaceOrderUseCase;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableFeignClients("pl.training.cloud.payments.adapters.rest")
@Configuration
public class ShopConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.training.cloud.shop.adapters.rest"))
                .build();
    }

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(PaymentsService paymentsService) {
        return new PlaceOrderService(paymentsService);
    }

}
