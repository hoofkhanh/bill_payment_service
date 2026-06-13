package bill_payment_service.repository;

import java.util.List;
import java.util.Optional;

import bill_payment_service.model.Bill;

public interface BillRepository {
	void save(Bill bill);

    Optional<Bill> findById(long id);

    List<Bill> findAll();

    void delete(long id);
}
