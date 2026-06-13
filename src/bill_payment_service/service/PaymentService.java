package bill_payment_service.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import bill_payment_service.model.Bill;
import bill_payment_service.model.BillState;
import bill_payment_service.model.Payment;
import bill_payment_service.model.PaymentStatus;
import bill_payment_service.repository.BillRepository;
import bill_payment_service.repository.PaymentRepository;

public class PaymentService {
	private final BillRepository billRepository;
	private final PaymentRepository paymentRepository;
	private final CustomerAccountService accountService;

	private long paymentId = 1;

	public PaymentService(BillRepository billRepository, PaymentRepository paymentRepository,
			CustomerAccountService accountService) {
		this.billRepository = billRepository;
		this.paymentRepository = paymentRepository;
		this.accountService = accountService;
	}

	public void pay(long billId) {

		Bill bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));

		if (bill.getState() == BillState.PAID) {
			throw new RuntimeException("Bill already paid");
		}

		accountService.withdraw(bill.getAmount());

		bill.setState(BillState.PAID);

		Payment payment = new Payment(paymentId++, bill.getId(), bill.getAmount(), LocalDate.now(),
				PaymentStatus.PROCESSED);

		paymentRepository.save(payment);
	}

	public void payMultiple(List<Long> billIds) {

		List<Bill> bills = billIds.stream()
				.map(id -> billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found")))
				.filter(b -> b.getState() == BillState.NOT_PAID).sorted(Comparator.comparing(Bill::getDueDate))
				.toList();

		long totalAmount = bills.stream().mapToLong(Bill::getAmount).sum();

		if (accountService.getBalance() < totalAmount) {

			throw new RuntimeException("Not enough fund");
		}

		for (Bill bill : bills) {
			pay(bill.getId());
		}
	}

	public List<Payment> getPaymentHistory() {
		return paymentRepository.findAll();
	}
}
