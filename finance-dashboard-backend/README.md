# 📊 Finance Dashboard Backend

A secure **Finance Dashboard Backend API** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**.
This system supports **ADMIN, ANALYST, and VIEWER roles** with financial record and dashboard management.

---

# 🚀 Features

✔ JWT Authentication
✔ Role-Based Access Control (ADMIN, ANALYST, VIEWER)
✔ User Management
✔ Financial Record Management
✔ Dashboard Summary APIs
✔ Monthly & Category Reports
✔ Recent Activity Tracking

---

# 🛠 Tech Stack

Java 17
Spring Boot
Spring Security
JWT Authentication
Spring Data JPA
MySQL
Maven
Lombok
Postman

---

# ▶️ How to Run the Project

Follow these steps to run the project locally.

## 1️⃣ Clone Repository

Open terminal and run:

git clone https://github.com/your-username/finance-dashboard-backend.git
cd finance-dashboard-backend

---

## 2️⃣ Open Project in IDE

Open using:

IntelliJ IDEA (Recommended)
Eclipse
VS Code

Wait until Maven dependencies download.

---

## 3️⃣ Create MySQL Database

Open MySQL and run:

CREATE DATABASE finance_dashboard;

---

## 4️⃣ Configure Database

Open:

src/main/resources/application.properties

Update:

spring.datasource.url=jdbc:mysql://localhost:3306/finance_dashboard
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Replace **your_password** with your MySQL password.

---

## 5️⃣ Run Application

Run:

FinanceDashboardApplication.java

OR:

mvn spring-boot:run

If successful:

Tomcat started on port 8080

Application runs at:

http://localhost:8080

---

# 🔐 Default Admin User

If database is empty, create admin manually:

INSERT INTO users (name,email,password,role)
VALUES ('Admin','[admin@gmail.com](mailto:admin@gmail.com)','$2a$10$exampleEncodedPassword','ADMIN');

Password before encoding:

123456

---

# 🧪 API Testing Using Postman

Base URL:

http://localhost:8080

---

# 🔐 Step 1 — Login (Generate JWT)

POST /api/auth/login

Body:

{
"email": "[admin@gmail.com](mailto:admin@gmail.com)",
"password": "123456"
}

Response:

JWT Token

Copy the token.

---

# 🔑 Step 2 — Set Authorization

In Postman:

Authorization → Bearer Token

Paste JWT Token.

Required for all APIs.

---

# 👥 Step 3 — Create User (Admin)

POST /api/users

Example:

{
"name": "Analyst User",
"email": "[analyst@gmail.com](mailto:analyst@gmail.com)",
"password": "123456",
"role": "ANALYST"
}

Create Viewer:

{
"name": "Viewer User",
"email": "[viewer@gmail.com](mailto:viewer@gmail.com)",
"password": "123456",
"role": "VIEWER"
}

---

# 👥 Step 4 — Get Users

GET /api/users

---

# 💰 Step 5 — Create Financial Record

POST /api/records/{userId}

Example:

{
"amount": 5000,
"type": "INCOME",
"category": "Salary",
"date": "2026-04-05",
"description": "Monthly salary"
}

---

# 📄 Step 6 — Get Records

GET /api/records/{userId}

---

# 🗑️ Step 7 — Delete Record

DELETE /api/records/{recordId}

---

# 📊 Dashboard APIs

Get Summary:

GET /api/dashboard/summary/{userId}

Category Summary:

GET /api/dashboard/category-summary/{userId}

Monthly Summary:

GET /api/dashboard/monthly-summary/{userId}

Recent Records:

GET /api/dashboard/recent/{userId}

---

# 🔒 Roles & Permissions

ADMIN → Create Users, View Users
ANALYST → Create Records
VIEWER → View Records Only

---

# 🗄 Database Tables

users
financial_records

Relation:

User (1) → (Many) FinancialRecord

---

# 📌 Tools Used

IntelliJ IDEA
MySQL Workbench
Postman
GitHub

---

# 👨‍💻 Author

Shashidhar Nandi
BE — Electronics and Communication
Java Full Stack Developer

GitHub:
https://github.com/your-username

---

# ⭐ Project Status

✔ Backend Completed
✔ JWT Authentication Implemented
✔ Role-Based Access Working
✔ APIs Tested
✔ Ready for Submission 🚀
