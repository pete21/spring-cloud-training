package pl.training.cloud.payments.adapters.logging;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.training.cloud.payments.domain.Payment;

//@Aspect
@Component
@Log
public class ConsolePaymentLogger {

    //@AfterReturning(value = "@annotation(pl.training.cloud.payments.application.PaymentProcess)", returning = "payment")
    public void log(Payment payment) {
        log.info("New payment: " + payment);
    }

    @EventListener
    public void onApplicationEvent(Payment payment) {
        log.info("New payment: " + payment);
    }

}
