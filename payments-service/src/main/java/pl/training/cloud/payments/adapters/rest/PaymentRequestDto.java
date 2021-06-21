package pl.training.cloud.payments.adapters.rest;

import lombok.Data;
import lombok.NonNull;
import org.javamoney.moneta.FastMoney;
import pl.training.cloud.commons.money.Money;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
public class PaymentRequestDto {

    @Money
    @NonNull
    private final String value;
    @NotEmpty
    private final Map<String, String> settings;

}
