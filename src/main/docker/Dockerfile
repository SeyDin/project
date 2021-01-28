FROM openjdk:8-jre-alpine
ARG JAR_FILE=demo-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]