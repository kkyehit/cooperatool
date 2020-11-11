FROM        openjdk:8
EXPOSE      5555
ARG         JAR_FILE=/target/WebServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} web.jar
ENTRYPOINT  ["java","-jar","web.jar"]