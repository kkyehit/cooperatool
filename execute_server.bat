@echo off
echo "Eureka 서버 실행 후 배치파일 실행"
setlocal Enabledelayedexpansion
set var[0]=ZuulServer
set var[1]=UserServer
set var[2]=RoomServer
set var[3]=WebServer
set var[4]=BoardServer
set var[5]=SourceServer
set var[6]=ChatServer

set jars[0]="zuulserver-0.0.1-SNAPSHOT.jar"
set jars[1]="userserver-0.0.1-SNAPSHOT.jar"
set jars[2]="roomserver-0.0.1-SNAPSHOT.jar"
set jars[3]="webserver-0.0.1-SNAPSHOT.jar"
set jars[4]="boardserver-0.0.1-SNAPSHOT.jar"
set jars[5]="sourceserver-0.0.1-SNAPSHOT.jar"
set jars[6]="chatserver-0.0.1-SNAPSHOT.jar"

for /L %%c in (0,1,6) do (
	cd D:\TeamViewerV.2\!var[%%c]!\target
	cd
	start cmd /c java -jar !jars[%%c]!
	echo start cmd /c java -jar !jars[%%c]!
	cd D:\TeamViewerV.2
)
pause