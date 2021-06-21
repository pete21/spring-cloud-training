package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.ProcessPaymentUseCase;

import javax.validation.Valid;

import static pl.training.cloud.commons.web.UriBuilder.requestUriWithId;

@Validated
@RestController
@RequiredArgsConstructor
public class ProcessPaymentController {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final RestPaymentsModelMapper modelMapper;

    @PostMapping("payments")
    public ResponseEntity<PaymentDto> process(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = modelMapper.toDomain(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = modelMapper.toDto(payment);
        var locationUri = requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

}
