package pl.training.cloud.payments.adapters.time;

import org.springframework.stereotype.Service;
import pl.training.cloud.payments.ports.time.TimeService;

import java.time.Instant;

@Service
public class SystemTimeService implements TimeService {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
