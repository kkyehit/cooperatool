FROM        openjdk:8
EXPOSE      5555
ARG         JAR_FILE=/target/EurekaServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} eureka.jar
ENTRYPOINT  ["java","-jar","eureka.jar"]