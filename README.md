# Document Expiry Tracker System

The Document Expiry Tracker System is a web-based application developed using Spring Boot. Its purpose is to help users manage personal documents such as ID cards, certificates, and licenses by keeping track of their expiry dates. The system sends timely reminders to avoid missing renewal deadlines.

---

## Features

- User registration and login
- Dashboard displaying all uploaded documents for each user
- Filter options to view:
   - All documents
   - Documents that have expired
   - Documents that will expire within the next 7 days
- Ability to add, update, and delete document records
- Automatic email notification 3 days before a document expires
- Clean and modular architecture following best practices

---

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Thymeleaf
- Bootstrap
- IntelliJ IDEA
- Git & GitHub

---

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven installed
- MySQL database running
- IntelliJ IDEA or any preferred IDE

---

### Installation Steps

1. Clone this repository:
   ```bash
   git clone https://github.com/Shobana1483/document_expirytracker_system1.git
   cd document_expirytracker_system1
   ```
2. Configure the Database
   - Create a new MySQL database (e.g., expiry_tracker_db). Then set the database connection in your environment or IntelliJ configuration.

   ### Configuration
   Before running the application, ensure that the following environment variables are configured:   

   - ***Required Environment Variables***
   
     | Variable Name   | Description                                             |
     | --------------- | ------------------------------------------------------- |
     | `DB_URL`        | JDBC URL of your MySQL database                         |
     | `DB_USERNAME`   | MySQL database username                                 |
     | `DB_PASSWORD`   | MySQL database password                                 |
     | `MAIL_USERNAME` | Gmail address for sending email notifications           |
     | `MAIL_PASSWORD` | Gmail App Password (⚠️ Not your regular Gmail password) |
 
   - ***Example .env File (for development)***
   ```bash
   DB_URL=jdbc:mysql://localhost:3306/expiry_tracker_db
   DB_USERNAME=root
   DB_PASSWORD=yourpassword
   MAIL_USERNAME=your_email@gmail.com
   MAIL_PASSWORD=your_app_password
   ```
*Note: To send emails using Gmail, you must enable 2FA on your account and generate an App Password.*

 - ***application.properties (reads from env)***
   ```bash
     # Database Configuration
     spring.datasource.url=${DB_URL}
     spring.datasource.username=${DB_USERNAME}
     spring.datasource.password=${DB_PASSWORD}
     
     # JPA & Hibernate
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
     
     # Thymeleaf
     spring.thymeleaf.cache=false
     spring.thymeleaf.prefix=classpath:/templates/
     spring.thymeleaf.suffix=.html
  
     # Mail Configuration
     spring.mail.host=smtp.gmail.com
     spring.mail.port=587
     spring.mail.username=${MAIL_USERNAME}
     spring.mail.password=${MAIL_PASSWORD}
     spring.mail.properties.mail.smtp.auth=true
     spring.mail.properties.mail.smtp.starttls.enable=true
   ```
   ### How to Run the Project  
   You can run the application from IntelliJ or via command line:
   ```bash
      mvn spring-boot:run    
   ```
   *Visit: http://localhost:8080*


---

## Future Enhancements

- Role-based access for users and administrators

- Option to receive SMS alerts along with email

- UI redesign for better visual appeal

- Dashboard analytics and downloadable reports

- Cloud deployment support





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