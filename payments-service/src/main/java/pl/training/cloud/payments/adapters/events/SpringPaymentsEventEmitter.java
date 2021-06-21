package pl.training.cloud.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.ports.events.PaymentsEventEmitter;

@Service
@RequiredArgsConstructor
public class SpringPaymentsEventEmitter implements PaymentsEventEmitter {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void emit(Payment payment) {
        applicationEventPublisher.publishEvent(payment);
    }

}
