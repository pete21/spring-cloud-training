package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;

@RestController
@RequiredArgsConstructor
public class GetPaymentController {

    private final GetPaymentUseCase getPaymentUseCase;
    private final RestPaymentsModelMapper modelMapper;

    @GetMapping("payments/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable String id) {
        var payment = getPaymentUseCase.findById(id);
        var paymentDto = modelMapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

}
