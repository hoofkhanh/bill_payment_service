this project is better if i can create service repositories because of the time limit, i can't make it successful.
This project would be better if i can catch a error that doesn't interrupt the program.

## Sample Commands
Please use all the commands below in the given order.

### 1. Add funds to account

```text
CASH_IN 10000000000
```

### 2. Create bills

```text
CREATE_BILL 1 ELECTRIC 200000 25/10/2026 EVN
CREATE_BILL 2 WATER 175000 30/10/2026 SAVACO
CREATE_BILL 3 INTERNET 800000 30/11/2026 VNPT
```

### 3. List all bills

```text
LIST_BILL
```

### 4. Search bills by provider

```text
SEARCH_BILL_BY_PROVIDER VNPT
```

### 5. View upcoming due bills

```text
DUE_DATE
```

### 6. Pay a single bill

```text
PAY 1
```

### 7. View payment history

```text
LIST_PAYMENT
```

### 8. Schedule a bill payment

```text
SCHEDULE 2 28/10/2026
```

### 9. View scheduled payments

```text
LIST_SCHEDULE
```

### 10. Pay multiple bills

```text
PAY 2 3
```

### 11. View payment history again

```text
LIST_PAYMENT
```

### 12. Update a bill

```text
UPDATE_BILL 3 INTERNET 850000 30/11/2026 NOT_PAID VNPT
```

### 13. Verify updated bill information

```text
LIST_BILL
```

### 14. Delete a bill

```text
DELETE_BILL 3
```

### 15. Verify bill deletion

```text
LIST_BILL
```

### 16. Exit the application

```text
EXIT
```

