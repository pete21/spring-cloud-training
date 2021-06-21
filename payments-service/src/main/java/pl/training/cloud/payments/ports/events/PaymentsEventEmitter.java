package pl.training.cloud.payments.ports.events;

import pl.training.cloud.payments.domain.Payment;

public interface PaymentsEventEmitter {

    void emit(Payment payment);

}
