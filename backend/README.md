# Hahn Software Backend

This is the backend REST API for the Hahn Software project, built with Spring Boot and Java 21. It provides endpoints for managing people and connects to a PostgreSQL database. The backend is designed to work seamlessly with the React frontend and can be run standalone or as part of a full stack via Docker Compose.

## Features
- RESTful API for managing people
- PostgreSQL database integration
- Exception handling and validation
- OpenAPI/Swagger documentation

## Prerequisites
- Java 21+
- Maven 3.9+
- (Optional) Docker & Docker Compose

## Running Locally (Maven)
1. **Install dependencies and build the JAR:**
   ```sh
   mvn clean package
   ```
2. **Run the application:**
   ```sh
   mvn spring-boot:run
   # or
   java -jar target/backend-0.0.1-SNAPSHOT.jar
   ```
3. **API will be available at:**
   - [http://localhost:8089](http://localhost:8089) (default, see `application.properties`)

## Running with Docker
1. **Build the Docker image:**
   ```sh
   docker build -t hahn-backend .
   ```
2. **Run the container:**
   ```sh
   docker run -p 8089:8089 --env SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:5432/postgres --env SPRING_DATASOURCE_USERNAME=admin --env SPRING_DATASOURCE_PASSWORD=adminpass hahn-backend
   ```

## Running with Docker Compose (Recommended)
From the project root:
```sh
docker-compose up --build
```
- The backend will be available at [http://localhost:8089](http://localhost:8089)
- The database and frontend will also be started automatically.

## Configuration
- **Port:** Default is `8089` (see `src/main/resources/application.properties`)
- **Database:**
  - `SPRING_DATASOURCE_URL` (e.g. `jdbc:postgresql://postgres:5432/postgres`)
  - `SPRING_DATASOURCE_USERNAME` (default: `admin`)
  - `SPRING_DATASOURCE_PASSWORD` (default: `adminpass`)
- **Swagger UI:** [http://localhost:8089/swagger-ui.html](http://localhost:8089/swagger-ui.html)

## API Documentation
- OpenAPI/Swagger docs are available at `/swagger-ui.html` when the app is running.

---

For more details, see the code and comments in this directory. 