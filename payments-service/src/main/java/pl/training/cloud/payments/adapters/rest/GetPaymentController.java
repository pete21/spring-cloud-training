package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;

@CacheConfig(cacheNames = "payments")
@RestController
@RequiredArgsConstructor
@Log
public class GetPaymentController implements GetPaymentApi {

    private final GetPaymentUseCase getPaymentUseCase;
    private final RestMapper mapper;

    @Cacheable(cacheNames = "payments")
    @Override
    public ResponseEntity<PaymentDto> findById(String id) {
        log.info("### Not using cache");
        var payment = getPaymentUseCase.findById(id);
        var paymentDto = mapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

}
