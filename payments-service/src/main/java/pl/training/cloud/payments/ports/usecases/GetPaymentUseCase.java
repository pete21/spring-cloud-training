package pl.training.cloud.payments.ports.usecases;

import pl.training.cloud.payments.domain.Payment;

public interface GetPaymentUseCase {

    Payment findById(String id);

}
