package bill_payment_service_test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bill_payment_service.model.Bill;
import bill_payment_service.model.BillState;
import bill_payment_service.model.BillType;
import bill_payment_service.repository.BillRepository;
import bill_payment_service.repository.BillRepositoryImplRepository;
import bill_payment_service.service.BillService;


public class BillServiceTest {

	@Test
    void shouldCreateBill() {

        BillRepository repository =
                new BillRepositoryImplRepository();

        BillService service =
                new BillService(repository);

        Bill bill =
                new Bill(
                        1,
                        BillType.ELECTRIC,
                        200000,
                        LocalDate.now(),
                        BillState.NOT_PAID,
                        "EVN"
                );

        service.createBill(bill);

        assertEquals(
                1,
                service.getAllBills().size()
        );
    }

    @Test
    void shouldDeleteBill() {

        BillRepository repository =
                new BillRepositoryImplRepository();

        BillService service =
                new BillService(repository);

        Bill bill =
                new Bill(
                        1,
                        BillType.ELECTRIC,
                        200000,
                        LocalDate.now(),
                        BillState.NOT_PAID,
                        "EVN"
                );

        service.createBill(bill);

        service.deleteBill(1);

        assertTrue(
                service.getAllBills().isEmpty()
        );
    }

    @Test
    void shouldSearchByProvider() {

        BillRepository repository =
                new BillRepositoryImplRepository();

        BillService service =
                new BillService(repository);

        service.createBill(
                new Bill(
                        1,
                        BillType.INTERNET,
                        800000,
                        LocalDate.now(),
                        BillState.NOT_PAID,
                        "VNPT"
                )
        );

        assertEquals(
                1,
                service.searchByProvider("VNPT")
                        .size()
        );
    }
}
