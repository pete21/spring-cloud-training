package pl.training.cloud.payments.application;

import lombok.Getter;
import lombok.ToString;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Getter
@ToString
public class PaymentRequest {

    private final FastMoney value;
    private final Map<String, String> properties;

    public PaymentRequest(FastMoney value, Map<String, String> properties) {
        validate(value);
        this.value = value;
        this.properties = properties;
    }

    private void validate(FastMoney value) {
        if (value.isNegativeOrZero()) {
            throw new IllegalArgumentException();
        }
    }

}
