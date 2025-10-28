# 🍴 Online Food Ordering System - Backend

This is the backend service for the **Online Food Ordering System**, developed using **Spring Boot**.  
It provides RESTful APIs for managing restaurants, users, orders, and food items.

---

## 🚀 Features
- User registration and authentication
- Restaurant and menu management
- Order creation and tracking
- Payment and billing support
- Global exception handling
- API documentation using **Swagger**

---

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **MySQL Database**
- **Swagger / OpenAPI**
- **Lombok**

---

## 📁 Project Structure
src/
├── main/
│ ├── java/orderapp/
│ │ ├── Controller/ → API endpoints
│ │ ├── Service/ → Business logic
│ │ ├── Repository/ → Data layer
│ │ ├── Entity/ → Model classes
│ │ └── Dto/ → Data Transfer Objects
│ └── resources/
│ ├── application.properties
│ └── static/
└── test/ → Unit tests


## ⚙️ Installation & Setup

1. Clone the repository  
   ```bash
   git clone https://github.com/MahantheshGowda17/Online-Food-Ordering-System-Backend.git
   
## Navigate to the project directory

cd Online-Food-Ordering-System-Backend

## Configure your MySQL database in application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/foodorderdb
spring.datasource.username=root
spring.datasource.password=yourpassword

## Run the project using Maven or Spring Boot

mvn spring-boot:run


### 2️⃣ **API Documentation**

Once the application is running, you can access the Swagger UI at:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## 👨‍💻 Author
**Mahanthesh Gowda H S**  
📍 Full Stack Developer | Java | Spring Boot | React  
🔗 [LinkedIn](https://www.linkedin.com/in/mahantheshgowda17)  
