# Use the official Maven image as the base image
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project's pom.xml file to the container
COPY pom.xml .

# Download the project dependencies
RUN  mvn dependency:go-offline

# Copy the rest of the project files to the container
COPY src/ ./src/

# Build the application
RUN #mvn package -DskipTests
RUN mvn clean install -DskipTests

# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file to the container
COPY --from=build /app/target/*.jar .

# Expose the port that the application runs on
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "spring-petstore-jaxrs-v2-1.0.0.jar"]
