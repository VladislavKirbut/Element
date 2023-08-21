FROM openjdk:latest
ARG JAR_FILE=target/elementapp-0.0.1-SNAPSHOT.jar
WORKDIR /element/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]