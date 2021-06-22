package pl.training.cloud.payments.adapters.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.training.cloud.commons.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    //@Money
    @NotNull
    private String value;
    @NotEmpty
    private Map<String, String> settings;

}
