package pl.training.cloud.payments.adapters.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentsRepository extends JpaRepository<PaymentEntity, String> {
}
