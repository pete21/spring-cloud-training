package pl.training.cloud.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class PaymentsBroker implements ApplicationRunner {

    private final PaymentsService paymentsService;

    public static void main(String[] args) {
        SpringApplication.run(PaymentsBroker.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        paymentsService.getPayments()
                .subscribe(payment -> log.info(payment.toString()),
                        exception -> log.info(exception.toString()),
                        () -> log.info("Completed"));

    }

}
