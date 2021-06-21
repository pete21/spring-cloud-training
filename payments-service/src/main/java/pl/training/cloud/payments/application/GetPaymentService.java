package pl.training.cloud.payments.application;

import lombok.RequiredArgsConstructor;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.ports.persistence.PaymentsQueries;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;

@RequiredArgsConstructor
public class GetPaymentService implements GetPaymentUseCase {

    private final PaymentsQueries paymentsQueries;

    @Override
    public Payment findById(String id) {
        return paymentsQueries.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
