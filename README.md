# Loan API - Backend Developer Case (Credit Module Challenge)

## Overview
This Loan API is designed for a bank to manage customer loans. It allows bank employees to create loans, list existing loans, retrieve installment details, and process loan payments. The API ensures business rules compliance, such as installment constraints, payment conditions, and customer loan limits.

## Features
- **Create Loan**: Add a new loan for a customer.
- **List Loans**: Retrieve all loans for a specific customer.
- **List Installments**: View all installments related to a particular loan.
- **Pay Loan**: Process installment payments for a loan.

## API Endpoints

### 1. Create Loan
**Endpoint:** `POST /api/loans`
**Description:** Creates a new loan for a customer.
**Request Body:**
```json
{
  "customer_id": "12345",
  "amount": 10000,
  "interest_rate": 0.2,
  "num_installments": 12
}
```
**Business Rules:**
- Customers must have enough credit limit.
- Allowed installment options: **6, 9, 12, 24** months.
- Interest rate range: **0.1 – 0.5**.
- Equal installment amounts.
- First installment due on the **first day of the next month**.

### 2. List Loans
**Endpoint:** `GET /api/loans?customer_id=12345`
**Description:** Retrieves all loans for a specific customer.
**Optional Filters:**
- `num_installments`
- `is_paid`

### 3. List Installments
**Endpoint:** `GET /api/loans/{loan_id}/installments`
**Description:** Retrieves all installments for a given loan.

### 4. Pay Loan
**Endpoint:** `POST /api/loans/{loan_id}/pay`
**Description:** Pays installments for a loan.
**Request Body:**
```json
{
  "amount": 500
}
```
**Business Rules:**
- Installments must be paid **wholly or not at all**.
- Pays the **earliest due installments first**.
- Cannot pay installments with a due date **more than 3 months ahead**.
- Response includes **installments paid, total amount spent, and loan status**.

## Database Schema

### Customers Table
| Column       | Type    | Description                |
|-------------|--------|----------------------------|
| id          | UUID   | Unique identifier          |
| name        | String | Customer name              |
| credit_limit | Float  | Maximum allowable credit  |

### Loans Table
| Column         | Type    | Description                      |
|---------------|--------|----------------------------------|
| id            | UUID   | Unique identifier               |
| customer_id   | UUID   | Reference to Customers Table    |
| amount        | Float  | Principal loan amount           |
| interest_rate | Float  | Interest rate applied           |
| total_amount  | Float  | Amount * (1 + interest rate)    |
| num_installments | Int | Number of installments         |
| is_paid       | Bool   | Loan fully paid status         |

### Installments Table
| Column        | Type    | Description                      |
|--------------|--------|----------------------------------|
| id           | UUID   | Unique identifier               |
| loan_id      | UUID   | Reference to Loans Table        |
| due_date     | Date   | Due date of the installment     |
| amount       | Float  | Installment amount              |
| is_paid      | Bool   | Paid status                     |

## Setup and Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/loan-api.git
   cd loan-api
   ```
2. Install dependencies:
   ```sh
   pip install -r requirements.txt
   ```
3. Configure database connection in `.env`.
4. Run migrations:
   ```sh
   python manage.py migrate
   ```
5. Start the API server:
   ```sh
   python manage.py runserver
   ```

## Testing
Run the tests using:
```sh
pytest
```

## API Response Example (Loan Payment)
```json
{
  "installments_paid": 2,
  "total_amount_spent": 200,
  "loan_fully_paid": false
}
```

## Contributors
- [Your Name]
- [Your Contact]

## License
This project is licensed under the MIT License.

