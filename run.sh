#!/bin/bash

# Step 1: Build the Docker image (which will build, test, and package the application)
echo "Building, testing, and packaging the Docker image..."
docker build -t transaction-service:latest .

# Step 2: Run the Docker container
echo "Running the Docker container on port 8080..."
docker run -p 8080:8080 transaction-service:latest
