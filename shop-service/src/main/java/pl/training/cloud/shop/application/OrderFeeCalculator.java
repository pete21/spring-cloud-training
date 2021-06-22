package pl.training.cloud.shop.application;

import lombok.Getter;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.cloud.commons.money.LocalMoney;


@Log
public class OrderFeeCalculator {

    @Getter
    private FastMoney value;

    public void setValue(long value) {
        this.value = LocalMoney.of(value);
        log.info("Updating order fee to: " +value);
    }

}
