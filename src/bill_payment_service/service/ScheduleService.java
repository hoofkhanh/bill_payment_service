package bill_payment_service.service;

import java.time.LocalDate;
import java.util.List;

import bill_payment_service.model.ScheduledPayment;
import bill_payment_service.repository.ScheduleRepository;

public class ScheduleService {
	private final ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public void schedulePayment(long billId, LocalDate executeDate) {
		ScheduledPayment schedule = new ScheduledPayment( billId, executeDate);

		scheduleRepository.save(schedule);
	}

	public List<ScheduledPayment> getSchedules() {

		return scheduleRepository.findAll();
	}

}
