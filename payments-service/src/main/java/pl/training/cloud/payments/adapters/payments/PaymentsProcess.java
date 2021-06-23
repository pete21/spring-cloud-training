package pl.training.cloud.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.training.cloud.payments.adapters.rest.PaymentDto;
import pl.training.cloud.payments.domain.Payment;

import javax.annotation.PostConstruct;
import java.net.URI;

@ConfigurationProperties("payments-broker")
@Service
@Log
@RequiredArgsConstructor
public class PaymentsProcess {

    private final DiscoveryClient discoveryClient;
    private final StreamMapper mapper;
    private final StreamBridge streamBridge;
    @Setter
    private String paymentsBrokerServiceName;
    @Setter
    private String paymentsResource;

    @PostConstruct
    public void init() {
        WebClient.builder().build()
                .get()
                .uri(getUri())
                .retrieve()
                .bodyToFlux(PaymentDto.class)
                .subscribe(payment -> log.info("Update: " + payment.toString()), exception -> log.info(exception.toString()), () -> log.info("Completed"));
    }

    @EventListener
    public void process(Payment payment) {
        WebClient.builder().build()
                .post()
                .uri(getUri())
                .bodyValue(mapper.toDto(payment))
                .retrieve()
                .bodyToMono(PaymentDto.class)
                .subscribe(this::onPayment, exception -> log.info(exception.toString()), () -> log.info("Completed"));
    }

    private void onPayment(PaymentDto paymentDto) {
        log.info("On finish payment: " + paymentDto.toString());
        streamBridge.send("paymentsChannel-out-0", paymentDto);
    }

    private URI getUri() {
        var instances = discoveryClient.getInstances(paymentsBrokerServiceName);
        var instance = instances.stream().findFirst()
                    .orElseThrow(IllegalStateException::new);
        return instance.getUri().resolve(paymentsResource);
    }

}
