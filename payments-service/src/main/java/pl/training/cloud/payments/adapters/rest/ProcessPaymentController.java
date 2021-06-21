package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.ProcessPaymentUseCase;

import static pl.training.cloud.commons.web.UriBuilder.requestUriWithId;

@RestController
@RequiredArgsConstructor
public class ProcessPaymentController {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final RestPaymentsModelMapper modelMapper;

    @PostMapping("payments")
    public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = modelMapper.toDomain(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = modelMapper.toDto(payment);
        var locationUri = requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

}
