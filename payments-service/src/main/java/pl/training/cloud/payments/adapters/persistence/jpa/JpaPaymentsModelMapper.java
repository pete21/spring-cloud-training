package pl.training.cloud.payments.adapters.persistence.jpa;

import org.mapstruct.Mapper;
import pl.training.cloud.payments.domain.Payment;

@Mapper(componentModel = "spring")
public interface JpaPaymentsModelMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toDomain(PaymentEntity paymentEntity);

}
