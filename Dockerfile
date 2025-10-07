FROM openjdk:21-jdk-slim

EXPOSE 8081

COPY target/hwConditionalApp-0.0.1-SNAPSHOT.jar myapp.jar


CMD ["java", "-jar", "myapp.jar"]