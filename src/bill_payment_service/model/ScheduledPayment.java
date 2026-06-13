package bill_payment_service.model;

import java.time.LocalDate;

public class ScheduledPayment {
	private long billId;
	private LocalDate executeDate;

	public ScheduledPayment(long billId, LocalDate executeDate) {
		this.billId = billId;
		this.executeDate = executeDate;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public LocalDate getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(LocalDate executeDate) {
		this.executeDate = executeDate;
	}

}
