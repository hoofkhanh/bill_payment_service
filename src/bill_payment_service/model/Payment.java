package bill_payment_service.model;

import java.time.LocalDate;

public class Payment {
	private long id;
	private long billId;
	private long amount;
	private LocalDate paymentDate;
	private PaymentStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Payment(long id, long billId, long amount, LocalDate paymentDate, PaymentStatus status) {
		super();
		this.id = id;
		this.billId = billId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.status = status;
	}

}
