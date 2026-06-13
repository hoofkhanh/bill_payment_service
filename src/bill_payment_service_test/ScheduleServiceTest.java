package bill_payment_service_test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bill_payment_service.repository.ScheduleImplRepository;
import bill_payment_service.repository.ScheduleRepository;
import bill_payment_service.service.ScheduleService;

public class ScheduleServiceTest {
	@Test
    void shouldCreateSchedule() {

        ScheduleRepository repository =
                new ScheduleImplRepository();

        ScheduleService service =
                new ScheduleService(repository);

        service.schedulePayment(
                1,
                LocalDate.now().plusDays(1)
        );

        assertEquals(
                1,
                service.getSchedules().size()
        );
    }

    @Test
    void shouldReturnSchedules() {

        ScheduleRepository repository =
                new ScheduleImplRepository();

        ScheduleService service =
                new ScheduleService(repository);

        service.schedulePayment(
                1,
                LocalDate.now()
        );

        assertFalse(
                service.getSchedules().isEmpty()
        );
    }
}
