package bill_payment_service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bill_payment_service.service.CustomerAccountService;

public class CustomerAccountServiceTest {
	@Test
    void shouldCashInSuccessfully() {

        CustomerAccountService service =
                new CustomerAccountService();

        service.cashIn(1000);

        assertEquals(
                1000,
                service.getBalance()
        );
    }

    @Test
    void shouldWithdrawSuccessfully() {

        CustomerAccountService service =
                new CustomerAccountService();

        service.cashIn(1000);

        service.withdraw(300);

        assertEquals(
                700,
                service.getBalance()
        );
    }
}
