# Use an OpenJDK image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file to the container
COPY build/libs/RoomService-0.0.1-SNAPSHOT.jar app.jar

# Expose the port RoomService runs on
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
