package pl.training.cloud.payments.adapters.persistence;

import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.ports.persistence.PaymentsQueries;
import pl.training.cloud.payments.ports.persistence.PaymentsUpdates;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    private final Set<Payment> payments = new HashSet<>();

    @Override
    public Optional<Payment> findById(String id) {
        return payments.stream()
                .filter(payment -> payment.hasId(id))
                .findFirst();
    }

    @Override
    public Payment save(Payment payment) {
        payments.add(payment);
        return payment;
    }

}
