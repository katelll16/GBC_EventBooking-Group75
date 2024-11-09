# Step 1: Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file from the local machine into the container
COPY build/libs/RoomService-0.0.1-SNAPSHOT.jar /app/roomservice.jar

# Step 4: Expose the port the app will run on (Spring Boot defaults to 8080)
EXPOSE 8080

# Step 5: Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "roomservice.jar"]
