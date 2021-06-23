package pl.training.cloud.shop;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.cloud.payments.adapters.rest.PaymentDto;
import pl.training.cloud.shop.application.OrderFeeCalculator;
import pl.training.cloud.shop.application.PlaceOrderService;
import pl.training.cloud.shop.ports.payments.PaymentsService;
import pl.training.cloud.shop.ports.usecases.PlaceOrderUseCase;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Consumer;

@Log
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
    public PlaceOrderUseCase placeOrderUseCase(PaymentsService paymentsService, OrderFeeCalculator orderFeeCalculator) {
        return new PlaceOrderService(paymentsService, orderFeeCalculator);
    }

    @RefreshScope
    @Bean
    public OrderFeeCalculator orderFeeCalculator(@Value("${order-fee}") long orderFee) {
        var calculator = new OrderFeeCalculator();
        calculator.setValue(orderFee);
        return calculator;
    }

    @Bean
    public Consumer<PaymentDto> paymentsChannel() {
        return paymentDto -> {
            log.info("Payment confirmation: " + paymentDto);
        };
    }

}
