1. Add funds to account
CASH_IN 1000000
2. Create bills
CREATE_BILL 1 ELECTRIC 200000 25/10/2026 EVN
CREATE_BILL 2 WATER 175000 30/10/2026 SAVACO
CREATE_BILL 3 INTERNET 800000 30/11/2026 VNPT
3. List all bills
LIST_BILL
4. Search bills by provider
SEARCH_BILL_BY_PROVIDER VNPT
5. View upcoming due bills
DUE_DATE
6. Pay a single bill
PAY 1
7. View payment history
LIST_PAYMENT
8. Schedule a bill payment
SCHEDULE 2 28/10/2026
9. View scheduled payments
LIST_SCHEDULE
10. Pay multiple bills
PAY 2 3
11. View payment history again
LIST_PAYMENT
12. Update a bill
UPDATE_BILL 3 INTERNET 850000 30/11/2026 NOT_PAID VNPT
13. Verify updated bill information
LIST_BILL
14. Delete a bill
DELETE_BILL 3
15. Verify bill deletion
LIST_BILL
16. Exit the application
EXIT
Error Handling Examples
Bill not found
PAY 999
Insufficient funds
CASH_IN 100
PAY 3
Provider not found
SEARCH_BILL_BY_PROVIDER UNKNOWN_PROVIDER
Invalid command
HELLO
