FROM        openjdk:8
EXPOSE      8700
ARG         JAR_FILE=/target/SourceServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} source.jar
ENTRYPOINT  ["java","-jar","source.jar"]