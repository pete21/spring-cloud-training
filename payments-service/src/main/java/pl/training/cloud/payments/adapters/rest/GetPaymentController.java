package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;

@RestController
@RequiredArgsConstructor
public class GetPaymentController implements GetPaymentApi {

    private final GetPaymentUseCase getPaymentUseCase;
    private final RestMapper mapper;

    @Override
    public ResponseEntity<PaymentDto> findById(String id) {
        var payment = getPaymentUseCase.findById(id);
        var paymentDto = mapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

}
