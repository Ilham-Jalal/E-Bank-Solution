# e-Bank Application
### Overview
The e-Bank application is an online banking platform developed using Spring Boot. It allows users to manage their bank accounts, cards, and perform financial transactions securely.

### Features
## Account Management
Create Bank Accounts: Users can create different types of accounts (checking, savings, etc.) with an initial balance and creation date.
View Balances and Transaction History: Users can check the current balance of their accounts and view detailed transaction history.
Close Accounts: Ability to close a bank account with a specified reason for closure.
## Card Management
View Card Information: Users can view details of their bank card (number, expiration date).
Activate and Deactivate Card: Functionality to activate or deactivate a bank card for transaction security.
Block Lost or Stolen Card: Users can block a bank card in case of loss or theft by providing a reason for blocking.
## Money Transfers
Internal Transfers: Users can transfer money between their own accounts with an amount and description.
External Transfers: Capability to transfer money to external accounts in other banks by specifying external account details (account number, bank name, etc.), amount, and description.
Beneficiary Management: Users can add, modify, or delete beneficiaries to facilitate external transfers.
Technical Requirements
Project Structure: Follows best practices for a Spring Boot application.
Code Documentation: Clear description of each class and method's responsibilities, along with parameter details and return values.
Business Logic: Data validation during account creation and closure, exception handling for illegal operations, and validation of amounts before transfers.
## Technologies Used
Java 21
Spring Boot 3.3.1
Spring Data JPA
MySQL
Maven
Lombok
Mockito for unit testing
## Installation
Clone the repository: git clone https://github.com/Ilham-Jalal/E-Bank-Solution.git
Import the project into your preferred IDE.
Configure MySQL database settings in application.properties.
Run the project as a Spring Boot application.
Contributions
Contributions to the project are welcome. Please open an issue to discuss proposed changes or improvements.

## Author
Your Name - @Ilham-Jalal

## License
This project is licensed under the MIT License - see the LICENSE file for details.
