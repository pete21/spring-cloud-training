package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.ports.usecases.ProcessPaymentUseCase;

import static pl.training.cloud.commons.web.UriBuilder.requestUriWithId;

@RestController
@RequiredArgsConstructor
public class ProcessPaymentController implements ProcessPaymentApi {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final RestMapper mapper;

    @Override
    public ResponseEntity<PaymentDto> process(PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toDomain(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

}
