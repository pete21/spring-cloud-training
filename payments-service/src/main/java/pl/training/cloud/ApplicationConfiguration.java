package pl.training.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

@EnableAspectJAutoProxy
@Configuration
public class ApplicationConfiguration {

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

}
