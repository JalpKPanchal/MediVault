# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/
COPY --from=builder /app/target/MediVault-0.0.1-SNAPSHOT.war app.war
EXPOSE 10000
CMD ["catalina.sh", "run"]