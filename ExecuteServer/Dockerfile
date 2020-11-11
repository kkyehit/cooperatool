FROM        openjdk:8
EXPOSE      8400
ARG         JAR_FILE=/target/ExecuteServer-0.0.1-SNAPSHOT.jar
ADD         ${JAR_FILE} exec.jar
RUN         apt-get update && \
            apt-get -y install g++ mono-mcs && \
            rm -rf /var/lib/apt/lists/*
ENTRYPOINT  ["java","-jar","exec.jar"]