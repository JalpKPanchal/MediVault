# Use an official Maven image to build the project
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy the project source
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy only the generated JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 9999

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
