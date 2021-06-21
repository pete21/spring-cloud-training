package pl.training.cloud.payments.application;

import lombok.RequiredArgsConstructor;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.domain.PaymentStatus;
import pl.training.cloud.payments.ports.persistence.PaymentsUpdates;
import pl.training.cloud.payments.ports.time.TimeService;
import pl.training.cloud.payments.ports.usecases.ProcessPaymentUseCase;

@RequiredArgsConstructor
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentsUpdates paymentsUpdates;
    private final TimeService timeService;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = createPayment(paymentRequest);
        return paymentsUpdates.save(payment);
    }

    private Payment createPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .properties(paymentRequest.getProperties())
                .timestamp(timeService.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }


}
