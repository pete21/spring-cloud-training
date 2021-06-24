package pl.training.cloud.shop.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.cloud.shop.ports.usecases.PlaceOrderUseCase;

@RequestMapping("orders")
@RestController
@RequiredArgsConstructor
public class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final RestMapper mapper;

    @PostMapping
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
