#!/bin/bash

# Step 1: Clean and build the project using Maven
echo "Building the project with Maven..."
mvn clean install

# Step 2: Build the Docker image
echo "Building Docker image..."
docker build -t transaction-service:latest .

# Step 3: Run the Docker container
echo "Running the Docker container..."
docker run -p 8080:8080 transaction-service:latest
