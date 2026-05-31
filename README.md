# Hotel Reservation Management System

A console-based Hotel Reservation Management System developed using Java, JDBC, and MySQL.

This project allows hotel staff to manage room reservations efficiently through a simple menu-driven interface.

---

## Features

- Reserve a Room
- View Reserved Rooms
- Search Room Number
- Update Reservation Details
- Delete Reservation
- MySQL Database Integration
- Menu Driven Interface
- JDBC Connectivity

---

## Technologies Used

- Java
- JDBC
- MySQL
- IntelliJ IDEA / Eclipse
- MySQL Connector J

---

## Project Structure

```
Hotel-Reservation-Management-System
│
├── Main.java
├── database.sql
├── README.md
└── mysql-connector-j.jar
```

---

## Database Configuration

### Create Database

```sql
CREATE DATABASE hotel_db;
```

### Use Database

```sql
USE hotel_db;
```

### Create Reservation Table

```sql
CREATE TABLE reservation(
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(20),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## JDBC Configuration

Update database credentials in Main.java

```java
private static final String url =
"jdbc:mysql://localhost:3306/hotel_db";

private static final String username = "root";

private static final String password = "root";
```

---

## How to Run

### Step 1

Clone Repository

```bash
git clone https://github.com/your-username/hotel-reservation-management-system.git
```

### Step 2

Import Project into IntelliJ IDEA or Eclipse

### Step 3

Add MySQL JDBC Driver

Download MySQL Connector/J and add it to project libraries.

### Step 4

Create Database and Table

Run the SQL commands provided above.

### Step 5

Run Main.java

---

## Menu Options

```text
1. Reserve a Room
2. View Reserved Rooms
3. Get Room Number
4. Update Reservation
5. Delete Reservation
0. Exit
```

---

## Sample Output

```text
HOTEL MANAGEMENT SYSTEM

1. Reserve a Room
2. View Reserved Rooms
3. Get Room Number
4. Update Reservation
5. Delete Reservation
0. Exit

Choose an option:
```

---

## Learning Outcomes

This project helped in understanding:

- JDBC Connectivity
- CRUD Operations
- SQL Queries
- Database Management
- Exception Handling
- Java Console Applications

---


## Author

Dikshant Chauhan

