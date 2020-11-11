cd D:\TeamViewerV.2\WebServer
kubectl delete deployment.apps/web-dp
mvn clean & mvn package & docker build -t cooperatoolcr.azurecr.io/web2 . & docker push cooperatoolcr.azurecr.io/web2 & kubectl apply -f deploy.yaml 