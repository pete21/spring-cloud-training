package pl.training.cloud.shop.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.shop.ports.usecases.PlaceOrderUseCase;

@RestController
@RequiredArgsConstructor
public class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final RestMapper mapper;

    @PostMapping("orders")
    public ResponseEntity<Void> placeOrder(@RequestBody OrderDto orderDto) {
        var order = mapper.toDomain(orderDto);
        placeOrderUseCase.place(order);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public String getOrder() {
        return "ok";
    }

}
