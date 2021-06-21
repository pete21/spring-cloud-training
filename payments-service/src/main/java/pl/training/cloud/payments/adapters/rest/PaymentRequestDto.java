package pl.training.cloud.payments.adapters.rest;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Data
public class PaymentRequestDto {

    private final String value;
    private final Map<String, String> settings;

}
