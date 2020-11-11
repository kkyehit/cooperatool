FROM        openjdk:8
EXPOSE      8080
ARG         JAR_FILE=/target/BoardServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} board.jar
ENTRYPOINT  ["java","-jar","board.jar"]