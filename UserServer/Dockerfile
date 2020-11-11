FROM        openjdk:8
EXPOSE      8080
ARG         JAR_FILE=/target/UserServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} user.jar
ENTRYPOINT  ["java","-jar","user.jar"]