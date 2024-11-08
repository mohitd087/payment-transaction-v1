# Use Maven with JDK 17 as the base image to build and run the application
FROM maven:3.9.0-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application and run tests
RUN mvn clean install

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/payment-transaction-v1-1.0.0.jar"]
