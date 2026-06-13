package bill_payment_service.service;

import java.util.Comparator;
import java.util.List;

import bill_payment_service.model.Bill;
import bill_payment_service.model.BillState;
import bill_payment_service.repository.BillRepository;

public class BillService {
	private final BillRepository repository;

	public BillService(BillRepository repository) {
		this.repository = repository;
	}

	public void createBill(Bill bill) {

		repository.save(bill);
	}

	public void updateBill(Bill bill) {

		repository.findById(bill.getId()).orElseThrow(() -> new RuntimeException("Bill not found"));

		repository.save(bill);
	}

	public void deleteBill(long billId) {

		repository.delete(billId);
	}

	public Bill getBill(long billId) {

		return repository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
	}

	public List<Bill> getAllBills() {

		return repository.findAll();
	}

	public List<Bill> searchByProvider(String provider) {

		return repository.findAll().stream().filter(bill -> bill.getProvider().equalsIgnoreCase(provider)).toList();
	}

	public List<Bill> getDueBills() {

		return repository.findAll().stream().filter(bill -> bill.getState() == BillState.NOT_PAID)
				.sorted(Comparator.comparing(Bill::getDueDate)).toList();
	}
}
