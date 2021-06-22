package pl.training.cloud.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private static final String STATUS = "CONFIRMED";

    private final MongoPaymentsRepository paymentsRepository;
    private final Sinks.Many<Payment> payments = Sinks.many().replay().all();

    public Flux<Payment> getPayments() {
        return payments.asFlux();
    }

    public Mono<Payment> process(Mono<Payment> sourcePayment) {
        return sourcePayment.map(this::processPayment)
                .flatMap(paymentsRepository::save)
                .flatMap(payment -> Mono.just(payment).delayElement(Duration.ofSeconds(5)))
                .doOnNext(payments::tryEmitNext);
    }

    private Payment processPayment(Payment payment) {
       return Payment.builder()
               .id(payment.getId())
               .requestId(payment.getRequestId())
               .value(payment.getValue())
               .properties(payment.getProperties())
               .status(STATUS)
               .build();
    }

}

