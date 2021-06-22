package pl.training.cloud.payments.adapters.persistence;

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
public class JpaPaymentsRepositoryAdapter implements PaymentsQueries, PaymentsUpdates {

    private final JpaPaymentsRepository paymentsRepository;
    private final PersistenceMapper mapper;

    @Override
    public Optional<Payment> findById(String id) {
        return paymentsRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Payment save(Payment payment) {
        var entity = mapper.toEntity(payment);
        var result = paymentsRepository.save(entity);
        return mapper.toDomain(result);
    }

}
