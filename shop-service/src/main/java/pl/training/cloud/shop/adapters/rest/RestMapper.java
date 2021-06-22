package pl.training.cloud.shop.adapters.rest;

import org.mapstruct.Mapper;
import pl.training.cloud.commons.money.LocalMoney;
import pl.training.cloud.shop.domain.Order;
import pl.training.cloud.shop.domain.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RestMapper {

    private static final List<Product> products = List.of(
            new Product(1L, "Spring in action", LocalMoney.of(200)),
            new Product(2L, "Spring Cloud in action", LocalMoney.of(200))
    );

    public Order toDomain(OrderDto orderDto) {
        // mapping
        return new Order(1L, products);
    }

}
