# Use an official OpenJDK runtime as a parent image for Java 21
FROM openjdk:21-jdk

# Define the argument for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/app.jar"]