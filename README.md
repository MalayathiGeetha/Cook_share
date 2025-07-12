# 🍽️ Cook Share - Recipe Sharing Platform

Cook Share is a full-stack web application that allows users to create, share, and explore recipes from around the world. Users can add recipes with full details, upload images, and browse recipes by categories and cuisine types.

## 🔧 Tech Stack

- ⚛️ **Frontend:** React (with Vite)
- 🎨 **UI Libraries:** Bootstrap, React-Bootstrap
- 🌐 **Backend:** Spring Boot (Java)
- ☁️ **REST APIs:** Axios for frontend-backend communication
- 💾 **Database:** MySQL (or your preferred DB)
- 🖼️ **Image Upload:** Multipart image upload (Spring Boot)

---

## ✨ Features

- 📝 Add a recipe with:
  - Title, description
  - Prep time and cook time
  - Cuisine (nationality), category
  - List of ingredients and instructions
  - Image upload
- 🔍 Filter and search recipes by:
  - Category
  - Cuisine
- 📸 Upload and preview recipe images
- 🧾 View recipe details with beautiful formatting
- 🪄 Responsive and user-friendly UI

---
🚀 How to Run the Project

📦 Backend (Spring Boot)
Open backend in IntelliJ or your IDE

Make sure MySQL DB is running

Configure application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/cookshare
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
Run the Spring Boot application:

./mvnw spring-boot:run


💻 Frontend (React with Vite)
Navigate to frontend folder:

cd cook-share-frontend
Install dependencies:

npm install
Run the development server:

npm run dev
Open in browser:

http://localhost:5173

