package pl.training.cloud.payments.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.ports.persistence.PaymentsUpdates;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.training.cloud.payments.PaymentsFixture.*;

@ExtendWith(MockitoExtension.class)
class ProcessPaymentServiceTests {

    private static final PaymentRequest VALID_PAYMENT_REQUEST = new PaymentRequest(PAYMENT_VALUE, emptyMap());

    @Mock
    private PaymentsUpdates paymentsUpdates;
    private ProcessPaymentService sut;

    @BeforeEach
    void beforeEach() {
        //sut = new ProcessPaymentService(() -> PAYMENT_ID, new FakePaymentsRepository(), () -> TIMESTAMP, (payment) -> {});
        when(paymentsUpdates.save(any(Payment.class))).then(returnsFirstArg());
        sut = new ProcessPaymentService(() -> PAYMENT_ID, paymentsUpdates, () -> PAYMENT_TIMESTAMP, (payment) -> {});
    }

    @Test
    void given_a_valid_payment_request_when_process_then_returns_a_valid_payment() {
        assertEquals(VALID_PAYMENT, sut.process(VALID_PAYMENT_REQUEST));
    }

    @Disabled("This is unnecessary coupling, its better to do an integration test")
    @Test
    void given_a_valid_payment_request_when_process_then_the_payment_is_persisted() {
        var payment = sut.process(VALID_PAYMENT_REQUEST);
        verify(paymentsUpdates).save(payment);
    }

}
