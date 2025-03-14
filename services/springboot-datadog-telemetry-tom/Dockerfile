# ====== Stage 1: Build Stage ======
FROM maven:3.9.9-eclipse-temurin-23-alpine AS builder

# Set working directory inside the container
WORKDIR /app

# Copy only the Maven project descriptor and download dependencies (faster rebuilds)
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn
RUN ./mvnw dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Build the Spring Boot JAR
RUN ./mvnw clean package -DskipTests

# ====== Stage 2: Runtime Stage ======
FROM eclipse-temurin:23.0.2_7-jre-alpine-3.21 AS runtime

LABEL site="johnmanko.com" 
LABEL app="telemetry-tom"
LABEL app_framework="springboot"
LABEL maintainer="John Manko"
LABEL app_group="Portfolio"

# Set working directory inside the container
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar
#COPY dd-java-agent.jar dd-java-agent.jar
ADD 'https://dtdg.co/latest-java-tracer' dd-java-agent.jar

# Run the Spring Boot application
ENTRYPOINT ["java", \
            "-javaagent:dd-java-agent.jar", \
            "-Ddd.profiling.enabled=true", \
            "-Ddd.trace.enabled=true", \
            "-XX:FlightRecorderOptions=stackdepth=256", \
            "-Ddd.logs.injection=true", \
            "-Ddd.service=telemetry-tom", \
            "-Ddd.env=staging", \
            "-Ddd.version=1.0", \
            "-jar", \
            "app.jar"]
