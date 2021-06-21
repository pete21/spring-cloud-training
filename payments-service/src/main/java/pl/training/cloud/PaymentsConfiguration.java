package pl.training.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.cloud.payments.application.GetPaymentService;
import pl.training.cloud.payments.application.PaymentIdGenerator;
import pl.training.cloud.payments.application.ProcessPaymentService;
import pl.training.cloud.payments.application.UUIDPaymentIdGenerator;
import pl.training.cloud.payments.ports.persistence.PaymentsQueries;
import pl.training.cloud.payments.ports.persistence.PaymentsUpdates;
import pl.training.cloud.payments.ports.time.TimeService;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;
import pl.training.cloud.payments.ports.usecases.ProcessPaymentUseCase;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentIdGenerator paymentIdGenerator, PaymentsUpdates paymentsUpdates, TimeService timeService) {
        return new ProcessPaymentService(paymentIdGenerator, paymentsUpdates, timeService);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsQueries paymentsQueries) {
        return new GetPaymentService(paymentsQueries);
    }

}
