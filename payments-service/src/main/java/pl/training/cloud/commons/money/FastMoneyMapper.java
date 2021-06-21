package pl.training.cloud.commons.money;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default FastMoney toFastMoney(String value) {
        return value != null ? FastMoney.parse(value) : LocalMoney.zero();
    }

    default String toText(FastMoney value) {
        return value != null ? value.toString() : "";
    }

}
