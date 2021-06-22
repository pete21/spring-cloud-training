package pl.training.cloud.payments.adapters.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.ports.usecases.GetPaymentUseCase;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.training.cloud.payments.PaymentsFixture.*;


@WebMvcTest(GetPaymentController.class)
@ExtendWith(SpringExtension.class)
class GetPaymentControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetPaymentUseCase getPaymentUseCase;
    @MockBean
    private RestMapper mapper;

    @BeforeEach
    void beforeEach() {
        when(getPaymentUseCase.findById(PAYMENT_ID)).thenReturn(VALID_PAYMENT);
        when(mapper.toDto(any(Payment.class))).thenReturn(paymentDto());
    }

    @Test
    void should_return_payment_by_id() throws Exception {
        mockMvc.perform(get("/payments/" + PAYMENT_ID)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PAYMENT_ID)))
                .andExpect(jsonPath("$.value", is(PAYMENT_VALUE.toString())));
    }

}