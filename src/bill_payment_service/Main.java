package bill_payment_service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bill_payment_service.model.Bill;
import bill_payment_service.model.BillState;
import bill_payment_service.model.BillType;
import bill_payment_service.model.Payment;
import bill_payment_service.repository.BillRepository;
import bill_payment_service.repository.BillRepositoryImplRepository;
import bill_payment_service.repository.PaymentImplRepository;
import bill_payment_service.repository.PaymentRepository;
import bill_payment_service.repository.ScheduleImplRepository;
import bill_payment_service.repository.ScheduleRepository;
import bill_payment_service.service.BillService;
import bill_payment_service.service.CustomerAccountService;
import bill_payment_service.service.PaymentService;
import bill_payment_service.service.ScheduleService;

public class Main {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		CustomerAccountService accountService = new CustomerAccountService();

		BillRepository billRepository = new BillRepositoryImplRepository();

		PaymentRepository paymentRepository = new PaymentImplRepository();

		ScheduleRepository scheduleRepository = new ScheduleImplRepository();

		BillService billService = new BillService(billRepository);

		PaymentService paymentService = new PaymentService(billRepository, paymentRepository, accountService);

		ScheduleService scheduleService = new ScheduleService(scheduleRepository);

		while (true) {

			String input = scanner.nextLine().trim();

			if (input.isEmpty()) {
			    continue;
			}

			String[] tokens = input.split("\\s+");
			String command = tokens[0].toUpperCase();

			switch (command) {

			case "CASH_IN":

				accountService.cashIn(Long.parseLong(tokens[1]));

				System.out.println("Your available balance: " + accountService.getBalance());

				break;

			// CREATE_BILL 1 ELECTRIC 200000 25/10/2026 EVN
			// CREATE_BILL <id> <type> <amount> <dueDate> <provider>
			case "CREATE_BILL":

				Bill bill = new Bill(Long.parseLong(tokens[1]), BillType.valueOf(tokens[2].toUpperCase()),
						Long.parseLong(tokens[3]), LocalDate.parse(tokens[4], FORMATTER), BillState.NOT_PAID,
						tokens[5]);

				billService.createBill(bill);

				System.out.println("Bill created successfully");

				break;

			// UPDATE_BILL 1 ELECTRIC 250000 30/10/2026 NOT_PAID EVN
			// UPDATE_BILL <id> <type> <amount> <dueDate> <state> <provider>
			case "UPDATE_BILL":

				Bill updatedBill = new Bill(Long.parseLong(tokens[1]), BillType.valueOf(tokens[2].toUpperCase()),
						Long.parseLong(tokens[3]), LocalDate.parse(tokens[4], FORMATTER),
						BillState.valueOf(tokens[5].toUpperCase()), tokens[6]);

				billService.updateBill(updatedBill);

				System.out.println("Bill updated successfully");

				break;

			// DELETE_BILL 1
			// DELETE_BILL <billId>
			case "DELETE_BILL":

				long deleteBillId = Long.parseLong(tokens[1]);

				billService.deleteBill(deleteBillId);

				System.out.println("Bill deleted successfully");

				break;

			// LIST_BILL
			case "LIST_BILL":

				printBills(billService.getAllBills());

				break;

			// SEARCH_BILL_BY_PROVIDER VNPT
			// SEARCH_BILL_BY_PROVIDER <provider>
			case "SEARCH_BILL_BY_PROVIDER":

				String provider = tokens[1];

				billService.searchByProvider(provider).forEach(System.out::println);

				break;

			// PAY 1
			// PAY 1 2 3
			// PAY <billId...>
			case "PAY":

				if (tokens.length == 2) {

					long billId = Long.parseLong(tokens[1]);

					paymentService.pay(billId);

					System.out.println("Payment has been completed for Bill with id " + billId);

				} else {

					List<Long> billIds = new ArrayList<>();

					for (int i = 1; i < tokens.length; i++) {

						billIds.add(Long.parseLong(tokens[i]));
					}

					paymentService.payMultiple(billIds);

					System.out.println("Payments completed successfully");
				}

				System.out.println("Current balance: " + accountService.getBalance());

				break;

			// DUE_DATE
			case "DUE_DATE":

				printBills(billService.getDueBills());

				break;

			// SCHEDULE <billId> <executeDate>
			case "SCHEDULE":

				long scheduleBillId = Long.parseLong(tokens[1]);

				LocalDate executeDate = LocalDate.parse(tokens[2], FORMATTER);

				scheduleService.schedulePayment(scheduleBillId, executeDate);

				System.out.println("Payment for bill id " + scheduleBillId + " is scheduled on " + executeDate);

				break;

			// LIST_SCHEDULE
			case "LIST_SCHEDULE":

				scheduleService.getSchedules().forEach(System.out::println);

				break;

			// LIST_PAYMENT
			case "LIST_PAYMENT":

				printPayments(
			            paymentService.getPaymentHistory()
			    );

				break;
			case "EXIT":

				System.out.println("Good bye");

				scanner.close();

				return;

			default:

				System.out.println("Unknown command");
			}
		}
	}
	
	private static void printBills(List<Bill> bills) {

	    System.out.printf(
	            "%-10s %-12s %-12s %-15s %-12s %-15s%n",
	            "Bill No.",
	            "Type",
	            "Amount",
	            "Due Date",
	            "State",
	            "Provider"
	    );

	    for (Bill bill : bills) {

	        System.out.printf(
	                "%-10d %-12s %-12d %-15s %-12s %-15s%n",
	                bill.getId(),
	                bill.getType(),
	                bill.getAmount(),
	                bill.getDueDate().format(FORMATTER),
	                bill.getState(),
	                bill.getProvider()
	        );
	    }
	}
	
	private static void printPayments(List<Payment> payments) {

	    System.out.printf(
	            "%-5s %-12s %-15s %-12s %-10s%n",
	            "No.",
	            "Amount",
	            "Payment Date",
	            "State",
	            "Bill Id"
	    );

	    for (Payment payment : payments) {

	        System.out.printf(
	                "%-5d %-12d %-15s %-12s %-10d%n",
	                payment.getId(),
	                payment.getAmount(),
	                payment.getPaymentDate().format(FORMATTER),
	                payment.getStatus(),
	                payment.getBillId()
	        );
	    }
	}

}
