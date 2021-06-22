package pl.training.cloud.payments;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {

    PaymentDto toDto(Payment payment);

    Payment toDomain(PaymentDto paymentDto);

}
