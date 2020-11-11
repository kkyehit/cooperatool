FROM        openjdk:8
EXPOSE      5555
ARG         JAR_FILE=/target/ZuulServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} zuul.jar
ENTRYPOINT  ["java","-jar","zuul.jar"]