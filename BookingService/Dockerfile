# Step 1: Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file of BookingService from the local machine into the container
COPY build/libs/BookingService-0.0.1-SNAPSHOT.jar /app/bookingservice.jar

# Step 4: Expose the port the app will run on
EXPOSE 8081

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "bookingservice.jar"]
