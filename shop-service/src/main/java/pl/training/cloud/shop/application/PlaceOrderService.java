package pl.training.cloud.shop.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.cloud.shop.domain.Order;
import pl.training.cloud.shop.ports.usecases.PlaceOrderUseCase;

@Log
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    @Override
    public void place(Order order) {
        var paymentValue = order.getTotalValue();
        // pay
        log.info("Payment value: " + paymentValue);
        // save and other logic
    }

}
