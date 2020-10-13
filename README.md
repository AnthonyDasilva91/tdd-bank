# Kata Bank Account

A simple bank application.

# Statement

Bank AccountDevelop an api that manages a bank accountRules:
- Write some code to create a bank application and to withdraw/deposit a validamount of money in/from the account.
- Write some code to transfer a specified amount of money from one bankaccount (the payer) to another (the payee).
- Write some code to keep a record of the transfer for both bank accounts in atransaction history
- Write some code to query a bank account's transaction history for any banktransfers to or from a specific account

# Implementation

The application is based on the Spring Framework and it uses the H2 database.

The application is structured with the following packages:
- domain: the business logic of the application.
- persistence: the persistence mechanism.
- presentation: the endpoints of the application. 
- usecase: all the usecases of the application. Each is responsible to do one specific action.

[Unit tests] (/src/test/java/com/tddbank/kata)
[Source code] (/src/main/java/com/tddbank/kata)
