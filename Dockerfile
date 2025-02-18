# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR to the container
COPY target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 9999  
# Change this if your app runs on a different port

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
