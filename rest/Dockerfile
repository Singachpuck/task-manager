FROM maven:3.9.6-eclipse-temurin-17-alpine

WORKDIR /usr/local/app

COPY ./ /usr/local/app/

RUN mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","./target/task-manager-0.0.1-SNAPSHOT.jar"]