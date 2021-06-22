package pl.training.cloud.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class PaymentDto {

    private String id;
    private String value;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
    private String requestId;

}
