# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY build/libs/ApprovalService-0.0.1-SNAPSHOT.jar app.jar

# Make the JAR file executable
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose the port the application will run on
EXPOSE 8080
