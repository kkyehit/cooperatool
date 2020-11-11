FROM        openjdk:8
EXPOSE      8700
ARG         JAR_FILE=/target/paintserver-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} paint.jar
ENTRYPOINT  ["java","-jar","paint.jar"]