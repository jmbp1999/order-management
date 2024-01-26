```markdown
# Order Management System

## ER Diagram
![Order Management ER Diagram](https://github.com/jmbp1999/order-management/assets/108087237/52349cff-ccc3-47d5-b9de-b2ba2e50dd68)

## DDL Scripts

```sql
CREATE SCHEMA `order_management`;

CREATE TABLE `order_management`.`order_table` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    itemName VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    shippingAddress VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    orderReferenceNumber VARCHAR(255) NOT NULL,
    placementTimestamp TIMESTAMP NOT NULL,
    userId BIGINT NOT NULL
);

CREATE TABLE `order_management`.`user_table` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255)
);
```

## Endpoints

### Authentication
SECURED APIs should be called with Bearer Token.

- **GET** - http://localhost:8080/auth/welcome
- **POST** - http://localhost:8080/auth/signup
    - Returns: String
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "securePassword"
  }
  ```
- **POST** - http://localhost:8080/auth/signin
    - Returns: JWT Token
  ```json
  {
    "userName": "john.doe@example.com",
    "password": "securePassword"
  }
  ```

### Order Management

#### Order Object
- **id**
- **itemName**
- **quantity**
- **shipping address**
- **orderStatus** (Possible values: ['NEW', 'DISPATCH', 'CANCEL'])
- **orderReferenceNumber**
- **placementTimestamp**

- **GET** - http://localhost:8080/orders/history
    - Returns: Array of Order
- **POST** - http://localhost:8080/orders/place
    - Returns: Reference Number

- **PUT** - http://localhost:8080/orders/{orderReference}/cancel

### Cron Job

- A separate process running independently.
- Responsible for transitioning orders from 'NEW' state to 'DISPATCH' state.
- Ideally, run on a separate server.
- Can store the timestamp of the last dispatch to optimize database queries.

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/jmbp1999/order-management.git
   ```

2. **Prerequisites:**
    - Docker
    - If running locally:
        - JDK 17

3. **Comment/Uncomment Application Properties:**
    - Depending on the environment (local, production, etc.), modify the application properties. Comment or uncomment relevant lines in the configuration files.

4. **Running Locally:**
    - Ensure JDK 17 is installed.
    - Run the application using your preferred build tool or IDE.

5. **Docker:**
    - Run the following command in the ROOT Directory
  ```bash
    docker-compose up
  ```
```