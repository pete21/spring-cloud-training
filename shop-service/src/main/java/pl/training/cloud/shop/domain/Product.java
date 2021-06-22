package pl.training.cloud.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.FastMoney;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private FastMoney price;

}
