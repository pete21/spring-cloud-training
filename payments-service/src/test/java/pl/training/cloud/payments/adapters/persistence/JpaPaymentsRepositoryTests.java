package pl.training.cloud.payments.adapters.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.training.cloud.payments.PaymentsFixture;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.training.cloud.payments.PaymentsFixture.PAYMENT_STATUS;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class JpaPaymentsRepositoryTests {

    private final PaymentEntity paymentEntity = PaymentsFixture.paymentEntity();

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private JpaPaymentsRepository sut;

    @BeforeEach
    void beforeEach() {
        entityManager.persist(paymentEntity);
        entityManager.flush();
    }

    @Test
    void given_payment_with_particular_status_in_database_when_find_by_status_then_returns_the_payment() {
        var actual = sut.findByStatus(PAYMENT_STATUS.name());
        assertTrue(actual.contains(paymentEntity));
    }

}