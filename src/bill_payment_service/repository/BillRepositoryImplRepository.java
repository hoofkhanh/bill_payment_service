package bill_payment_service.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bill_payment_service.model.Bill;

public class BillRepositoryImplRepository implements BillRepository {
	Map<Long, Bill> bills = new HashMap<>();

	@Override
    public void save(Bill bill) {
        bills.put(bill.getId(), bill);
    }

    @Override
    public Optional<Bill> findById(long id) {
        return Optional.ofNullable(
                bills.get(id)
        );
    }

    @Override
    public List<Bill> findAll() {
        return new ArrayList<>(
                bills.values()
        );
    }

    @Override
    public void delete(long id) {
        bills.remove(id);
    }

}
