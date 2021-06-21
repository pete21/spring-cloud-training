package pl.training.cloud.payments.adapters.rest;

import lombok.Data;
import pl.training.cloud.commons.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class PaymentRequestDto {

    @Money
    @NotNull
    private final String value;
    @NotEmpty
    private final Map<String, String> settings;

}
