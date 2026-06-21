# UPI Payment System

A production-style **UPI Payment System** developed using **Spring Boot**, **Java**, **MySQL**, and **Layered Architecture**. The project simulates real-world UPI payment workflows including user management, wallet operations, payment processing, transaction tracking, and VPA validation.

***

## 📌 Project Overview

This application demonstrates how digital payment systems like **Google Pay**, **PhonePe**, and **Paytm UPI** work internally.

The system supports:

* User registration and login
* Wallet creation and balance management
* UPI (VPA) validation
* Money transfer between users
* Transaction history tracking
* Payment status monitoring
* Strategy-based payment routing

***

## 🏗️ Architecture

```text
Client
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

### Design Patterns Used

* Strategy Pattern
* DTO Pattern
* Repository Pattern
* Mapper Pattern
* Singleton Beans (Spring)
* Layered Architecture

***

# 📂 Project Structure

```text
src/main/java/com/example/upipayment
│
├── common
│   ├── dto
│   ├── enums
│   ├── exception
│   └── util
│
├── config
│
├── user
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── mapper
│   └── dto
│
├── wallet
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── mapper
│   └── dto
│
├── payment
│   ├── controller
│   ├── service
│   ├── strategy
│   └── dto
│
├── transaction
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── mapper
│   └── dto
│
└── upi
    ├── controller
    ├── service
    ├── mapper
    └── dto
```

***

# 🚀 Features

## User Module

* Register new user
* User login
* Fetch user information
* Link bank account
* Generate unique User ID

***

## Wallet Module

* Wallet auto-creation on registration
* Credit wallet
* Debit wallet
* Check wallet balance
* Wallet status management

***

## UPI Module

* Validate VPA format
* Verify VPA existence
* Route payment provider
* Payment party validation

***

## Payment Module

* Validate payment request
* Execute money transfer
* Strategy-based provider routing
* Process transactions securely

***

## Transaction Module

* Create transaction records
* Track payment lifecycle
* Update transaction status
* View transaction history

***

# 💳 Payment Flow

```text
1. User Initiates Payment
            │
            ▼
2. Validate Sender VPA
            │
            ▼
3. Validate Receiver VPA
            │
            ▼
4. Check Wallet Balance
            │
            ▼
5. Create Transaction (INITIATED)
            │
            ▼
6. Process Payment
            │
            ▼
7. Debit Sender Wallet
            │
            ▼
8. Credit Receiver Wallet
            │
            ▼
9. Update Transaction Status
            │
            ▼
10. Return Response
```

***

# 🔄 Transaction Lifecycle

```text
INITIATED
     │
     ▼
VALIDATED
     │
     ▼
PROCESSING
     │
 ┌───┴─────┐
 ▼         ▼
SUCCESS   FAILED
             │
             ▼
          REVERSED
```

***

# 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Build Tool

* Maven

### Utilities

* Lombok
* Bean Validation

### Testing

* JUnit 5

***

# 🗄️ Database Configuration

Create database:

```sql
CREATE DATABASE upi_payment_system;
```

Update configuration:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/upi_payment_system
    username: root
    password: root
```

***

# ▶️ Running the Project

## Clone Repository

```bash
git clone https://github.com/YS0702/UPI-Payment-System.git
```

## Navigate to Project

```bash
cd UPI-Payment-System
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

***

# 📡 API Endpoints

## User APIs

### Register User

```http
POST /api/v1/users/register
```

### Login

```http
POST /api/v1/users/login
```

### Get User

```http
GET /api/v1/users/{userId}
```

### Link Bank Account

```http
POST /api/v1/users/{userId}/link-bank
```

***

## Wallet APIs

### Get Balance

```http
GET /api/v1/wallets/{userId}/balance
```

### Credit Wallet

```http
POST /api/v1/wallets/{userId}/credit
```

### Debit Wallet

```http
POST /api/v1/wallets/{userId}/debit
```

***

## Payment APIs

### Initiate Payment

```http
POST /api/v1/payments/initiate
```

### Validate Payment

```http
POST /api/v1/payments/validate
```

### Payment Status

```http
GET /api/v1/payments/{transactionId}
```

***

## Transaction APIs

### Get Transaction

```http
GET /api/v1/transactions/{transactionId}
```

### User Transaction History

```http
GET /api/v1/transactions/users/{userId}
```

### Update Status

```http
PATCH /api/v1/transactions/{transactionId}/status
```

***

## UPI APIs

### Validate VPA

```http
GET /api/v1/upi/validate/{vpa}
```

### Route Transaction

```http
GET /api/v1/upi/route/{vpa}
```

***

# 🧪 Sample Payment Request

```json
{
  "senderUserId": "USR-123",
  "senderVpa": "yash@upi",
  "receiverVpa": "akshata@upi",
  "amount": 500,
  "remarks": "Dinner",
  "providerType": "INTERNAL_WALLET"
}
```

***

# 👥 Team Development Workflow

## Daily Sync

```bash
git switch main
git pull origin main

git switch <your-branch>
git merge main
```

***

## Push Changes

```bash
git add .
git commit -m "Implemented module changes"
git push origin <your-branch>
```

***

## Create Pull Request

```text
Base Branch    : main
Compare Branch : your-branch
```

After review:

```text
Merge Pull Request
```

***

# 📈 Future Enhancements

* JWT Authentication
* Swagger Documentation
* Redis Caching
* Kafka Event Processing
* Distributed Transactions
* Notification Service
* Bank API Integration
* Docker Support
* Kubernetes Deployment
* CI/CD Pipeline

***

## License

This project is built for educational, learning, interview preparation, and backend system design practice purposes.
