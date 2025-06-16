# My Introduction Portfolio

This project serves as an introductory personal portfolio showcasing a blend of modern web development technologies. It's built with a focus on a responsive, dynamic user experience powered by server-side rendering and lightweight interactivity.

## Technologies Used

* **Java**: The core programming language for the backend logic.
* **Spring Framework**: The powerful and widely-used framework for building robust, scalable Java applications. This project leverages Spring Boot for rapid application development and Spring Data JPA for data persistence.
* **HTMX**: A lightweight library that allows you to access modern browser features directly from HTML, enabling dynamic updates without writing much JavaScript. This is used to create a more interactive user experience with minimal client-side complexity.
* **Docker**: For containerization, ensuring a consistent development and deployment environment. It allows the application and its dependencies (like the database) to be packaged and run reliably across different environments.
* **PostgreSQL**: A powerful, open-source relational database system used for storing application data.

## Project Features (Implied by structure)

* **Dynamic Content Loading**: Utilizes HTMX for efficient loading of different sections (like experiences, interests, contact info) without full page reloads.
* **User Profiles**: Manages primary user profile data.
* **Experience Section**: Displays work or project experiences.
* **Interests Section**: Showcases personal interests.
* **Contact Form**: Provides a way for visitors to send messages.
* **Data Initialization**: Includes a `CommandLineRunner` to pre-populate the database with sample profile data upon application startup for quick testing and demonstration.

## Getting Started

### Prerequisites

* Java 21 or higher
* Maven (for building the project)
* Docker & Docker Compose (for running the database)

### Setup and Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/PhuNguyenPT/Introduction
    cd Introduction
    ```

2.  **Start PostgreSQL with Docker Compose:**
    Ensure Docker is running on your system. Navigate to the root of the project and run:
    ```bash
    docker-compose up -d postgres
    ```
    This will start a PostgreSQL container in the background.

3.  **Configure Application Properties:**
    Check `src/main/resources/application.properties` (or `application.yml`) to ensure your database connection settings match your Docker setup (e.g., username, password, database name, and port).

4.  **Build and Run the Application:**
    You can build and run the Spring Boot application using Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    Alternatively, you can run it from your IDE (e.g., IntelliJ IDEA, VS Code).

5.  **Access the Application:**
    Once the application starts, open your web browser and navigate to:
    ```
    http://localhost:8080/profiles
    ```

    The `DataInitializer` will automatically create a default user (`user`/`password`) and a primary profile, so you should see content immediately.

---
