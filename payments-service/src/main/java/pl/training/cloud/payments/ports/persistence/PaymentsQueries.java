package pl.training.cloud.payments.ports.persistence;

import pl.training.cloud.payments.domain.Payment;

import java.util.Optional;

public interface PaymentsQueries {

    Optional<Payment> findById(String id);

}
