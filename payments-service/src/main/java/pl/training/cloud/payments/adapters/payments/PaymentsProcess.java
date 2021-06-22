package pl.training.cloud.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.training.cloud.payments.domain.Payment;

import java.net.URI;

@ConfigurationProperties("payments-broker")
@Service
@Log
@RequiredArgsConstructor
public class PaymentsProcess {

    private final DiscoveryClient discoveryClient;
    @Setter
    private String paymentsBrokerServiceName;
    @Setter
    private String paymentsResource;

    @EventListener
    public void process(Payment payment) {
        log.info(getUri().toString());
    }

    private URI getUri() {
        var instances = discoveryClient.getInstances(paymentsBrokerServiceName);
        var instance = instances.stream().findFirst()
                    .orElseThrow(IllegalStateException::new);
        return instance.getUri().resolve(paymentsResource);
    }

}
