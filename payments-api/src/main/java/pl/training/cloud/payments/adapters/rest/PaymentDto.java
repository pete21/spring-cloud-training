package pl.training.cloud.payments.adapters.rest;

import lombok.Data;

import java.time.Instant;

@Data
public class PaymentDto {

    private String id;
    private String requestId;
    private String value;
    private Instant timestamp;
    private PaymentStatusDto status;

}
