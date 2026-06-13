package bill_payment_service.repository;

import java.util.List;
import java.util.Optional;

import bill_payment_service.model.Payment;

public interface PaymentRepository {
	void save(Payment payment);

    Optional<Payment> findById(long id);

    List<Payment> findAll();

    void delete(long id);
}
