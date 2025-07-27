# Document Expiry Tracker System

This is a Spring Boot-based web application designed to help users manage and track the expiry of essential documents such as government IDs, certificates, and licenses. The system allows users to store document details and alerts them ahead of expiry to ensure timely renewals.

---

## Features

- Add, update, and delete documents with expiry dates.
- Automatically track and organize documents based on their expiry.
- Clean and maintainable backend architecture.
- Easy integration with future enhancements like email or SMS notifications.
- RESTful APIs for efficient document management.

---

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- IntelliJ IDEA
- Git & GitHub

---

## How to Run the Project

### Prerequisites

- Java 8 or later installed
- MySQL database
- Maven
- Any IDE (Recommended: IntelliJ IDEA)

### Steps

1. *Clone the Repository*
   ```bash
   git clone https://github.com/shobanajagadeesan3/document_expirytracker_system1.git
   cd document_expirytracker_system1

2. *Configure the Database*

Create a new database in MySQL.

Update the application.properties file inside src/main/resources/:

spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password



3. *Run the Application*

You can run the application using your IDE or through the terminal:

mvn spring-boot:run



4. *Access the API*

The application will be available at:

http://localhost:8080/





---

## Future Enhancements

- Implement user authentication.

- Add email/SMS reminder functionality for document expiry.

- Build a frontend UI using HTML/CSS/React (optional).



---

## About the Developer



Hi 👋 I'm *Shobana J*, a passionate and dedicated Computer Science Engineering student from Jerusalem College of Engineering. I enjoy building meaningful projects that solve real-world problems using technologies like Java, Spring Boot, SQL, HTML, and CSS.

This project is a part of my continuous learning journey to become a skilled software developer. I'm always open to learning new technologies and collaborating on innovative ideas.

- 🌐 *GitHub:* https://github.com/Shobana1483
- 💼 *LinkedIn:* *LinkedIn:* [www.linkedin.com/in/shobana-jagadeesan](https://www.linkedin.com/in/shobana-jagadeesan)
- 📫 *Email:* shobanajagadeesan3[at]gmail[dot]com



---

## License

This project is open-source and available for use under the MIT License.