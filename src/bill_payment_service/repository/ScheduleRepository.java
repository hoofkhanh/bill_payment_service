package bill_payment_service.repository;

import java.util.List;
import java.util.Optional;

import bill_payment_service.model.ScheduledPayment;

public interface ScheduleRepository {
	void save(
            ScheduledPayment schedule
    );

    Optional<ScheduledPayment> findById(
            long id
    );

    List<ScheduledPayment> findAll();

    void delete(long id);
}
