package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;

@RestController
@RequiredArgsConstructor
@Log
public class GetPaymentController implements GetPaymentApi {

    private final GetPaymentUseCase getPaymentUseCase;
    private final RestMapper mapper;

    @Cacheable("payments")
    @Override
    public ResponseEntity<PaymentDto> findById(String id) {
        log.info("Using normal flow (not using cache)");
        var payment = getPaymentUseCase.findById(id);
        var paymentDto = mapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

}
