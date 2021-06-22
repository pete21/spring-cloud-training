package pl.training.cloud.shop.adapters.rest;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long ownerId;
    private List<Long> products;

}
