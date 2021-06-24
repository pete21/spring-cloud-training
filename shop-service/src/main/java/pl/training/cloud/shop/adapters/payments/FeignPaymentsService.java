package pl.training.cloud.shop.adapters.payments;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.training.cloud.payments.adapters.rest.PaymentRequestDto;
import pl.training.cloud.payments.adapters.rest.ProcessPaymentApi;
import pl.training.cloud.shop.domain.Payment;
import pl.training.cloud.shop.ports.payments.PaymentsService;

import java.util.Map;
import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class FeignPaymentsService implements PaymentsService {

    private final ProcessPaymentApi processPaymentApi;

    @CircuitBreaker(name = "pay", fallbackMethod = "payFallback")
    @Retry(name = "pay")
    //@Retry(attempts = 2)
    @Override
    public Optional<Payment> pay(FastMoney value, Map<String, String> properties) {
        var paymentRequest = new PaymentRequestDto(value.toString(), properties);
        try {
            var paymentDto = processPaymentApi.process(paymentRequest).getBody();
            if (paymentDto == null) {
                return Optional.empty();
            }
            var payment = new Payment(paymentDto.getId(), paymentDto.getStatus().name());
            return Optional.of(payment);
        } catch (HttpClientErrorException exception) {
            log.warning("Payment failed: " + exception.getMessage());
        }
        return Optional.empty();
    }
    public Optional<Payment> payFallback(FastMoney value, Map<String, String> properties, Throwable throwable)  {
        log.info("Executing fallback method: " + throwable.getMessage());
        return Optional.empty();
    }

}
