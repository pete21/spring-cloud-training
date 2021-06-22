package pl.training.cloud.shop.ports.payments;

import org.javamoney.moneta.FastMoney;
import pl.training.cloud.shop.domain.Payment;

import java.util.Map;
import java.util.Optional;

public interface PaymentsService {

    Optional<Payment> pay(FastMoney value, Map<String, String> properties);

}
