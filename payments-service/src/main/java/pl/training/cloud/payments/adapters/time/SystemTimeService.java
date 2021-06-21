package pl.training.cloud.payments.adapters.time;

import pl.training.cloud.payments.ports.time.TimeService;

import java.time.Instant;

public class SystemTimeService implements TimeService {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
