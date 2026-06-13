package bill_payment_service.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bill_payment_service.model.ScheduledPayment;

public class ScheduleImplRepository implements ScheduleRepository {
	private final Map<Long, ScheduledPayment> schedules = new HashMap<>();

	@Override
	public void save(ScheduledPayment schedule) {
		schedules.put(schedule.getBillId(), schedule);
	}

	@Override
	public Optional<ScheduledPayment> findById(long billId) {
		return Optional.ofNullable(schedules.get(billId));
	}

	@Override
	public List<ScheduledPayment> findAll() {
		return new ArrayList<>(schedules.values());
	}

	@Override
	public void delete(long id) {
		schedules.remove(id);
	}
}
