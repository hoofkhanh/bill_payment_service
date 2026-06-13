package bill_payment_service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import bill_payment_service.model.Bill;
import bill_payment_service.model.BillState;
import bill_payment_service.model.BillType;
import bill_payment_service.repository.BillRepository;
import bill_payment_service.repository.BillRepositoryImplRepository;
import bill_payment_service.repository.PaymentImplRepository;
import bill_payment_service.repository.PaymentRepository;
import bill_payment_service.service.CustomerAccountService;
import bill_payment_service.service.PaymentService;

public class PaymentServiceTest {
	@Test
	void shouldPayBillSuccessfully() {

		BillRepository billRepo = new BillRepositoryImplRepository();

		PaymentRepository paymentRepo = new PaymentImplRepository();

		CustomerAccountService account = new CustomerAccountService();

		account.cashIn(1000);

		PaymentService service = new PaymentService(billRepo, paymentRepo, account);

		Bill bill = new Bill(1, BillType.WATER, 200, LocalDate.now(), BillState.NOT_PAID, "SAVACO");

		billRepo.save(bill);

		service.pay(1);

		assertEquals(800, account.getBalance());

		assertEquals(BillState.PAID, bill.getState());
	}

	@Test
	void shouldThrowWhenBillNotFound() {

		BillRepository billRepo = new BillRepositoryImplRepository();

		PaymentRepository paymentRepo = new PaymentImplRepository();

		CustomerAccountService account = new CustomerAccountService();

		PaymentService service = new PaymentService(billRepo, paymentRepo, account);

		assertThrows(RuntimeException.class, () -> service.pay(999));
	}

	@Test
	void shouldThrowWhenNotEnoughFund() {

		BillRepository billRepo = new BillRepositoryImplRepository();

		PaymentRepository paymentRepo = new PaymentImplRepository();

		CustomerAccountService account = new CustomerAccountService();

		PaymentService service = new PaymentService(billRepo, paymentRepo, account);

		billRepo.save(new Bill(1, BillType.WATER, 500, LocalDate.now(), BillState.NOT_PAID, "SAVACO"));

		assertThrows(RuntimeException.class, () -> service.pay(1));
	}

	@Test
	void shouldPayMultipleBills() {

		BillRepository billRepo = new BillRepositoryImplRepository();

		PaymentRepository paymentRepo = new PaymentImplRepository();

		CustomerAccountService account = new CustomerAccountService();

		account.cashIn(10000);

		PaymentService service = new PaymentService(billRepo, paymentRepo, account);

		billRepo.save(new Bill(1, BillType.WATER, 100, LocalDate.now(), BillState.NOT_PAID, "A"));

		billRepo.save(new Bill(2, BillType.ELECTRIC, 200, LocalDate.now(), BillState.NOT_PAID, "B"));

		service.payMultiple(List.of(1L, 2L));

		assertEquals(9700, account.getBalance());
	}
}
