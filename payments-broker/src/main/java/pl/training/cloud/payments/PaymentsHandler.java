package pl.training.cloud.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PaymentsHandler {

    private final PaymentsService paymentsService;
    private final RestMapper mapper;

    public Mono<ServerResponse> getPayments(ServerRequest serverRequest) {
        var payments = paymentsService.getPayments().map(mapper::toDto);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(payments, PaymentDto.class);
    }

    public Mono<ServerResponse> process(ServerRequest serverRequest) {
        var payment = paymentsService
                .process(serverRequest.bodyToMono(PaymentDto.class).map(mapper::toDomain))
                .map(mapper::toDto);
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(payment, PaymentDto.class);
    }

}
