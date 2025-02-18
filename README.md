Loan API README
Overview
This README provides an overview of the Loan API, which allows bank employees to manage loans for customers. The API includes functionality to create loans, list loans, list installments, and pay installments.

Features
Create Loan: Create a new loan for a customer with validation checks.
List Loans: Retrieve a list of loans for a specific customer.
List Installments: View all installments associated with a specific loan.
Pay Loan: Make payments towards installments of a loan with specific rules.
API Endpoints
1. Create Loan
Endpoint: POST /api/loans
Description: Creates a new loan for a given customer.
Request Body:
json
Copy code
{
  "customerId": "string",
  "amount": "number",
  "interestRate": "number",
  "installments": "number"
}
Response:
201 Created: Loan created successfully.
400 Bad Request: Validation errors.
2. List Loans
Endpoint: GET /api/loans
Description: Lists loans for a given customer.
Query Parameters:
customerId: Unique identifier for the customer.
installments: (Optional) Filter by number of installments.
isPaid: (Optional) Filter by loan payment status.
Response:
200 OK: Returns a list of loans.
3. List Installments
Endpoint: GET /api/loans/{loanId}/installments
Description: Lists installments for a given loan.
Response:
200 OK: Returns a list of installments.
4. Pay Loan
Endpoint: POST /api/loans/{loanId}/pay
Description: Pay installments for a given loan.
Request Body:
json
Copy code
{
  "amount": "number"
}
Response:
200 OK: Returns the result of the payment operation.
400 Bad Request: Validation errors.
Database Schema
Customer Table
customerId: UUID
name: String
limit: Decimal
Loan Table
loanId: UUID
customerId: UUID
amount: Decimal
interestRate: Decimal
installments: Integer
isPaid: Boolean
Installment Table
installmentId: UUID
loanId: UUID
dueDate: Date
amount: Decimal
isPaid: Boolean
Setup Instructions
Clone the Repository:

bash
Copy code
git clone <repository-url>
cd <repository-directory>
Install Dependencies:

bash
Copy code
mvn install
Configure Database:

Update the application.properties file with your database connection details.
Run the Application:

bash
Copy code
mvn spring-boot:run
Access the API:

The API will be available at http://localhost:8080/api/loans.
Testing the API
Use tools like Postman or curl to test the API endpoints.
Ensure to test all endpoints with valid and invalid data to verify error handling.
Error Handling
The API will return appropriate HTTP status codes and messages for errors, such as validation issues or insufficient funds.
Conclusion
This Loan API provides a robust solution for managing loans within a banking application, ensuring compliance with business rules while offering a user-friendly interface for bank employees.

License
This project is licensed under the MIT License - see the LICENSE file for details.

Contact
For any questions or issues, please contact Okan Ozman at okanozman@yahoo.com.tr.
