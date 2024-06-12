FROM openjdk:17-jdk-alpine
LABEL authors="Oguz.Turan"
WORKDIR /app
COPY target/k8sexample-0.0.1-SNAPSHOT.jar /app/k8sexample.jar
ENTRYPOINT ["java", "-jar", "k8sexample.jar"]
EXPOSE 8080