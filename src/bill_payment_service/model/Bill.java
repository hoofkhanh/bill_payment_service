package bill_payment_service.model;

import java.time.LocalDate;

public class Bill {
	private long id;
	private BillType type;
	private long amount;
	private LocalDate dueDate;
	private BillState state;
	private String provider;

	public Bill(long id, BillType type, long amount, LocalDate dueDate, BillState state, String provider) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.dueDate = dueDate;
		this.state = state;
		this.provider = provider;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}
