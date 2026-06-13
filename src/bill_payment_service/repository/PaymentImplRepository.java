package bill_payment_service.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bill_payment_service.model.Payment;

public class PaymentImplRepository implements PaymentRepository {
	private final Map<Long, Payment> payments =
            new HashMap<>();

    @Override
    public void save(Payment payment) {
        payments.put(
                payment.getId(),
                payment
        );
    }

    @Override
    public Optional<Payment> findById(long id) {
        return Optional.ofNullable(
                payments.get(id)
        );
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(
                payments.values()
        );
    }

    @Override
    public void delete(long id) {
        payments.remove(id);
    }
}
