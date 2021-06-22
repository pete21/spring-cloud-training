package pl.training.cloud.payments;

import org.mapstruct.Mapper;
import pl.training.cloud.commons.money.FastMoneyMapper;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface RestMapper {

    PaymentDto toDto(Payment payment);

    Payment toDomain(PaymentDto paymentDto);

}
