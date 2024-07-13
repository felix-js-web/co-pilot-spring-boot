# Use OpenJDK 21 as the base image for building the application
#FROM openjdk:21-jdk-slim as build
FROM eclipse-temurin:21.0.2_13-jdk  as build

# Set the working directory in the Docker container
WORKDIR /app

# Copy the Gradle configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Grant execution permissions to the gradlew script
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build

# Use OpenJDK 21 JRE for the runtime image
FROM eclipse-temurin:21.0.2_13-jdk

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app.jar"]