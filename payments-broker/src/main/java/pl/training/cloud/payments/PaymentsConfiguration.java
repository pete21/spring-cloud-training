package pl.training.cloud.payments;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.training.cloud.commons.money.FastMoneyMapper;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public RouterFunction<ServerResponse> routes(PaymentsHandler paymentsHandler) {
        return RouterFunctions
                .route(GET("payments").and(accept(APPLICATION_JSON)), paymentsHandler::getPayments)
                .andRoute(POST("/payments").and(accept(APPLICATION_JSON)), paymentsHandler::process);
    }

    @Bean
    public FastMoneyMapper fastMoneyMapper() {
        return Mappers.getMapper(FastMoneyMapper.class);
    }

}
