FROM openjdk:11-jdk-slim

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/voting-0.0.1-SNAPSHOT.jar $PROJECT_HOME/voting-0.0.1-SNAPSHOT.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "./voting-0.0.1-SNAPSHOT.jar"]