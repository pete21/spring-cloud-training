package pl.training.cloud.payments.adapters.persistence;

import org.mapstruct.Mapper;
import pl.training.cloud.payments.domain.Payment;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toDomain(PaymentEntity paymentEntity);

}
