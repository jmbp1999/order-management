# Use the official Maven image as the build stage
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project source code into the container
COPY . .

# Build the project with Maven
RUN mvn clean package

# Use the official OpenJDK 17 image as the runtime stage
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/target/*.jar order-management.jar

# Expose the port on which the Spring Boot application will run
EXPOSE 8080

# Specify the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "order-management.jar"]
