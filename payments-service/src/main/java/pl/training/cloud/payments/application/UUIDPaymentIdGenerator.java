package pl.training.cloud.payments.application;

import java.util.UUID;

public class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
