FROM        openjdk:8
EXPOSE      8500
ARG         JAR_FILE=/target/chatserver-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} chat.jar
ENTRYPOINT  ["java","-jar","chat.jar"]