package pl.training.cloud.payments.adapters.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("payments-service")
@Validated
public interface ProcessPaymentApi {

    @PostMapping("payments")
    ResponseEntity<PaymentDto> process(@Valid @RequestBody PaymentRequestDto paymentRequestDto);

}
