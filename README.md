
# Job Listing Portal

## 📌 Project Overview
The Job Listing Portal is a full-stack web application that connects job seekers and employers on a single platform. Employers can post and manage job vacancies, while job seekers can search and apply for jobs securely.

## 🚀 Features
- User registration and login
- JWT-based authentication
- Role-based access control (Employer & Job Seeker)
- Job posting and management
- Advanced job search with filters
- Job application with resume upload

## 🛠️ Technology Stack
### Backend
- Java 21
- Spring Boot
- Spring Security
- MongoDB
- JWT
- Maven

### Frontend
- React
- HTML, CSS, JavaScript
- Axios

---

## ▶️ How to Run Backend (Spring Boot)

### 🔧 Prerequisites
- Java 21
- Maven
- MongoDB (running locally)
- IDE (IntelliJ / Eclipse / STS)

### 📂 Backend Setup Steps

1️⃣ Extract the backend project  
2️⃣ Start MongoDB (default: mongodb://localhost:27017/jobportal)

3️⃣ Configure `application.properties`
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/jobportal
server.port=8080

jwt.secret=ThisIsAVeryStrongAndSecureJWTSecretKeyForHS256Algorithm12345
jwt.expiration=86400000
```

4️⃣ Build the project
```bash
mvn clean install
```

5️⃣ Run the application
```bash
mvn spring-boot:run
```

### ✅ Backend URL
```
http://localhost:8080
```

---

## ▶️ How to Run Frontend (React)

```bash
npm install
npm start
```

Frontend URL:
```
http://localhost:3000
```

---

## 🔐 Security
- JWT-based authentication
- Role-based API access
- Authorization header:
```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🎓 Use Case
Suitable for academic projects, internships, and full-stack learning.

