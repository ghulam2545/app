# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk AS runner
WORKDIR /app

# Copy the built jar from builder
COPY --from=builder /app/target/*.jar ./app.jar

# Expose port
EXPOSE 8089

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
