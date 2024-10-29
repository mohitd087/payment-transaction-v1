# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/payment-transaction-v1-1.0.0.jar transaction-service.jar

# Expose port 8080 to access the application outside the container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
