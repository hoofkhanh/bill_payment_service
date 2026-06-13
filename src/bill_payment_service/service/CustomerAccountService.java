package bill_payment_service.service;

public class CustomerAccountService {
	private long balance;

	public void cashIn(long amount) {

		if (amount <= 0) {
			throw new IllegalArgumentException();
		}

		balance += amount;
	}

	public void withdraw(long amount) {

		if (balance < amount) {
			throw new RuntimeException("Not enough fund");
		}

		balance -= amount;
	}

	public long getBalance() {
		return balance;
	}
}
