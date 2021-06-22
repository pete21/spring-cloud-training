package pl.training.cloud.payments.adapters.persistence;

import lombok.Data;
import org.javamoney.moneta.FastMoney;
import pl.training.cloud.commons.money.FastMoneyConverter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Table(name = "payments")
@Entity
@Data
public class PaymentEntity {

    @Id
    private String id;
    @Convert(converter = FastMoneyConverter.class)
    private FastMoney value;
    @ElementCollection
    @CollectionTable(name = "PAYMENTS_PROPERTIES", joinColumns = @JoinColumn(name = "payment_id"))
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    private Map<String, String> properties;
    private Instant timestamp;
    private String status;
    @Version
    private Long version;

    @Override
    public boolean equals(Object otherPayment) {
        if (this == otherPayment) {
            return true;
        }
        if (!(otherPayment instanceof PaymentEntity)) {
            return false;
        }
        var payment = (PaymentEntity) otherPayment;
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
