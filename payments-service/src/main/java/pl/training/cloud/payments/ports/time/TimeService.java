package pl.training.cloud.payments.ports.time;

import java.time.Instant;

public interface TimeService {

    Instant getTimestamp();

}
