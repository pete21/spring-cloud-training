package pl.training.cloud.payments.adapters.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface GetPaymentApi {

    @GetMapping("payments/{id}")
    ResponseEntity<PaymentDto> findById(@PathVariable String id);

}
