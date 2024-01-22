# Order Management System

## ER Diagram
![Order Management ER Diagram](https://github.com/jmbp1999/order-management/assets/108087237/52349cff-ccc3-47d5-b9de-b2ba2e50dd68)

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
  - Returns: Order
  ```json
  {
    "itemName": "Product A",
    "quantity": 2,
    "shippingAddress": "123 Main St, City",
  }
  ```
- **PUT** - http://localhost:8080/orders/{order_id}/cancel

### Cron Job

- A separate process running independently.
- Responsible for transitioning orders from 'NEW' state to 'DISPATCH' state.
- Ideally, run on a separate server.
- Can store the timestamp of the last dispatch to optimize database queries.
