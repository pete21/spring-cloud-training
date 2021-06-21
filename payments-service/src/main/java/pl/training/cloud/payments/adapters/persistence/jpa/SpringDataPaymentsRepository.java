package pl.training.cloud.payments.adapters.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataPaymentsRepository extends JpaRepository<PaymentEntity, String> {

    @Query("select p from PaymentEntity p where p.status = :status order by p.timestamp asc")
    List<PaymentEntity> findByStatus(@Param("status") String status);

}
