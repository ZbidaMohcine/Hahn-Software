# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Package Stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
# If a pre-built JAR exists, use it; otherwise, use the one from the build stage
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar
# Uncomment the next line to use a locally built JAR instead of building in Docker
# COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"] 