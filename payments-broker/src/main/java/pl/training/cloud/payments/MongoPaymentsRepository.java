package pl.training.cloud.payments;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoPaymentsRepository extends ReactiveMongoRepository<Payment, String> {
}
