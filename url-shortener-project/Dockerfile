# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/url-shortener.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
