package pl.training.cloud.payments;

import org.javamoney.moneta.FastMoney;
import pl.training.cloud.commons.money.LocalMoney;
import pl.training.cloud.payments.adapters.persistence.PaymentEntity;
import pl.training.cloud.payments.adapters.rest.PaymentDto;
import pl.training.cloud.payments.adapters.rest.PaymentStatusDto;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.domain.PaymentStatus;

import java.time.Instant;

import static java.util.Collections.emptyMap;

public class PaymentsFixture {

    public static final PaymentStatus PAYMENT_STATUS = PaymentStatus.STARTED;
    public static final FastMoney PAYMENT_VALUE = LocalMoney.of(1_000);
    public static final String PAYMENT_ID = "0001";
    public static final Instant PAYMENT_TIMESTAMP = Instant.now();
    public static final Payment VALID_PAYMENT = Payment.builder()
            .id(PAYMENT_ID)
            .timestamp(PAYMENT_TIMESTAMP)
            .value(PAYMENT_VALUE)
            .properties(emptyMap())
            .status(PAYMENT_STATUS)
            .build();
    public static PaymentEntity paymentEntity() {
        var entity = new PaymentEntity();
        entity.setId(PAYMENT_ID);
        entity.setProperties(emptyMap());
        entity.setStatus(PAYMENT_STATUS.name());
        entity.setTimestamp(PAYMENT_TIMESTAMP);
        entity.setValue(PAYMENT_VALUE);
        return entity;
    }
    public static PaymentDto paymentDto() {
        var dto = new PaymentDto();
        dto.setId(PAYMENT_ID);
        dto.setStatus(PaymentStatusDto.NOT_CONFIRMED);
        dto.setTimestamp(PAYMENT_TIMESTAMP);
        dto.setValue(PAYMENT_VALUE.toString());
        return dto;
    }

}
