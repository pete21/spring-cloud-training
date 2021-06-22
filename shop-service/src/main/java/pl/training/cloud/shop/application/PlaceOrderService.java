package pl.training.cloud.shop.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.cloud.shop.domain.Order;
import pl.training.cloud.shop.ports.payments.PaymentsService;
import pl.training.cloud.shop.ports.usecases.PlaceOrderUseCase;

import java.util.Collections;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Log
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final PaymentsService paymentsService;
    private final OrderFeeCalculator orderFeeCalculator;

    @Override
    public void place(Order order) {
        var paymentValue = order.getTotalValue().add(orderFeeCalculator.getValue());
        log.info("Payment value: " + paymentValue);
        var payment = paymentsService.pay(paymentValue, Map.of("cardNumber", "123456789"))
                .orElseThrow(PaymentFailedException::new);
        log.info(payment.toString());
        // save and other logic
    }

}
