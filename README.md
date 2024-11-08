# payment-transaction-v1

# Transaction Service

## Requirements
- Docker

## Endpoints
1. `POST /accounts`: Creates a new account.
2. `GET /accounts/{accountId}`: Retrieves account information.
3. `POST /transactions`: Creates a transaction for an account.

## Running the Service

### With Docker
1. Build and run with:
   ```bash
   ./run.sh
### Interacting with the Database,
H2 in memory db is being used,
We can login into H2 console with below url and credentials:
http://localhost:8080/h2-console
username = sa
password = password
jdbc-url= jdbc:h2:mem:testdb
Table and operation Type data would the created during startup of application.

### Testing the services.
We can test individual services using below curls:
Before running the application make sure that port 8080 is open and not already being used otherwise will see error in console.

docker: Error response from daemon: driver failed programming external connectivity on endpoint hopeful_villani (c88b36d49b0ea68237f20942b936905b84c3f3b339652c1d5aece25068d33a2d): Bind for 0.0.0.0:8080 failed: port is already allocated.

1. Create Account
   ```bash
   curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
   '{
   "document_number":"123456788"
   }' \
   'http://localhost:8080/accounts'
   ```
2. Get Account
   ```bash
   curl -i -X GET \
   'http://localhost:8080/accounts/1'
   ```
3. Create Transaction
   ```bash
   curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
   '{
   "account_id": 1,
   "operation_type_id": 1,
   "amount": 123.45
   }' \
   'http://localhost:8080/transactions'
   ```

### JUnit Test cases has been added for all API and would be ran before startup automatically by script.Logs can be checked with more details.

### Global Exception Handling has also been added for better visibility and flexibility.

### Validation for the input field has been added for maintainability.
   
