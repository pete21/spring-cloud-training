package pl.training.cloud.payments.ports.persistence;

import pl.training.cloud.payments.domain.Payment;

public interface PaymentsUpdates {

    Payment save(Payment payment);

}
