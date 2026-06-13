package bill_payment_service.model;

public class CustomerAccount {
	private long balance;


	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public CustomerAccount(long balance) {
		this.balance = balance;
	}
	
}
