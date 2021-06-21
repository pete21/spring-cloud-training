package pl.training.cloud.payments.ports.usecases;

import pl.training.cloud.payments.application.PaymentRequest;
import pl.training.cloud.payments.domain.Payment;

public interface ProcessPaymentUseCase {

    Payment process(PaymentRequest paymentRequest);

}
