FROM openjdk:8
#copy같은거
#앞에있는jar파일을 컨테이너에 저장할 뒤에 jar파일에 복사하는거
ADD /target/databaseserver-0.0.1-SNAPSHOT.jar database-container.jar
ENTRYPOINT ["java", "-jar", "database-container.jar"]
