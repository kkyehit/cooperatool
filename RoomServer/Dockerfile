FROM        openjdk:8
EXPOSE      8300
ARG         JAR_FILE=/target/roomserver-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} room.jar
ENTRYPOINT  ["java","-jar","room.jar"]