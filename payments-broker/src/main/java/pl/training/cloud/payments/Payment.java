package pl.training.cloud.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    private String requestId;
    private FastMoney value;
    private Map<String, String> properties;
    private Instant timestamp;
    private String status;

}
