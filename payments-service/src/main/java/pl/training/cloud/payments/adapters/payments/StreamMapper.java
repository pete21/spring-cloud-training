package pl.training.cloud.payments.adapters.payments;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import pl.training.cloud.commons.money.FastMoneyMapper;
import pl.training.cloud.payments.adapters.rest.PaymentDto;
import pl.training.cloud.payments.adapters.rest.PaymentRequestDto;
import pl.training.cloud.payments.adapters.rest.PaymentStatusDto;
import pl.training.cloud.payments.application.PaymentRequest;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.domain.PaymentStatus;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface StreamMapper {

    @Mapping(source = "settings", target = "properties")
    PaymentRequest toDomain(PaymentRequestDto paymentRequestDto);

    @InheritInverseConfiguration
    PaymentDto toDto(Payment payment);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    PaymentStatusDto toDto(PaymentStatus status);

}
