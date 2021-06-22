package pl.training.cloud.shop.ports.usecases;

import pl.training.cloud.shop.domain.Order;

public interface PlaceOrderUseCase {

    void place(Order order);

}
