# Loan API - Backend Developer Case

## Overview
This Loan API is designed for a bank to manage customer loans. It allows bank employees to create loans, list existing loans, retrieve installment details, and process loan payments. The API ensures business rules compliance, such as installment constraints, payment conditions, and customer loan limits.

## Features
- **Create Loan**: Add a new loan for a customer.
- **List Loans**: Retrieve all loans for a specific customer.
- **List Installments**: View all installments related to a particular loan.
- **Pay Loan**: Process installment payments for a loan.

  
## Technologies Used
- **Spring Boot (Java)
- **Spring MVC
- **Spring Data JPA
- **H2 / MySQL Database
- **Postman (For Testing)


## API Endpoints

### 1. Create Loan
**Endpoint:** `POST /api/loans`
**Description:** Creates a new loan for a customer.
**Request Body:**
```json
{
  "customerId": 1,
  "amount": 15000.0,
  "interestRate": 0.3,
  "installment": 9
}
```
**Business Rules:**
- Customers must have enough credit limit.
- Allowed installment options: **6, 9, 12, 24** months.
- Interest rate range: **0.1 – 0.5**.
- Equal installment amounts.
- First installment due on the **first day of the next month**.

### 2. List Loans
**Endpoint:** `GET /api/loans/{customerId}`
**Description:** Retrieves all loans for a specific customer.
**Optional Filters:**
- `installment`
- `isPaid`

### 3. List Installments
**Endpoint:** `GET /api/loans/installments/{loanId}`
**Description:** Retrieves all installments for a given loan.

### 4. Pay Loan
**Endpoint:** `POST /api/loans/pay/{loanId}](http://localhost:8080/api/loans/pay/1?amount=850`
**Description:** Pays installments for a loan.
**Business Rules:**
- Installments must be paid **wholly or not at all**.
- Pays the **earliest due installments first**.
- Cannot pay installments with a due date **more than 3 months ahead**.
- Response includes **installments paid, total amount spent, and loan status**.

## Database Schema (Spring Boot JPA)

### Entity: Customer
```java
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Double creditLimit;
}
```

### Entity: Loan
```java
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    private Double amount;
    private Double interestRate;
    private Double totalAmount;
    private Integer installment;
    private Boolean isPaid;
}
```

### Entity: Installment
```java
@Entity
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Loan loan;
    private LocalDate dueDate;
    private Double amount;
    private Boolean isPaid;
}
```

## Setup and Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/loan-api.git
   cd loan-api
   ```
2. Configure database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:h2:mem:test
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.driverClassName=org.h2.Driver
   ```
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

## Testing
Run the tests using:
```sh
mvn test
```

## API Response Example (Loan Payment)
```json
{
  "installmentsPaid": 2,
  "totalAmountSpent": 200,
  "loanFullyPaid": false
}
```

## Contributors
- OKAN OZMAN  
- okanozman@yahoo.com.tr

## License
This project is licensed under the MIT License.

