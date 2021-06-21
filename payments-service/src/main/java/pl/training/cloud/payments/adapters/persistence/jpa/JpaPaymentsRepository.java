package pl.training.cloud.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.training.cloud.payments.domain.Payment;
import pl.training.cloud.payments.ports.persistence.PaymentsQueries;
import pl.training.cloud.payments.ports.persistence.PaymentsUpdates;

import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JpaPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    private final SpringDataPaymentsRepository paymentsRepository;
    private final JpaPaymentsModelMapper modelMapper;

    @Override
    public Optional<Payment> findById(String id) {
        return paymentsRepository.findById(id)
                .map(modelMapper::toDomain);
    }

    @Override
    public Payment save(Payment payment) {
        var entity = modelMapper.toEntity(payment);
        var result = paymentsRepository.save(entity);
        return modelMapper.toDomain(result);
    }

}
