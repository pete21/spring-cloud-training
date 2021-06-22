package pl.training.cloud.shop.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.training.cloud.payments.adapters.rest.PaymentDto;
import pl.training.cloud.payments.adapters.rest.PaymentRequestDto;
import pl.training.cloud.payments.adapters.rest.ProcessPaymentApi;
import pl.training.cloud.shop.domain.Payment;
import pl.training.cloud.shop.ports.payments.PaymentsService;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@Log
@RequiredArgsConstructor
public class FeignPaymentsService implements PaymentsService {

    private final ProcessPaymentApi processPaymentApi;

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

}
