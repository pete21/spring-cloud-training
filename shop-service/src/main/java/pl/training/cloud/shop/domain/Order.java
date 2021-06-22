package pl.training.cloud.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.FastMoney;
import pl.training.cloud.commons.money.LocalMoney;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long ownerId;
    private List<Product> products;

    public FastMoney getTotalValue() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(LocalMoney.zero(), FastMoney::add);
    }

}
