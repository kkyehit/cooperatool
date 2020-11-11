cd D:\
kubectl create secret generic mysql-info --from-file=./MySQL_URL --from-file=./MySQL_PORT --from-file=./MySQL_USER --from-file=./MySQL_PW  --from-file=./MySQL_DB

cd D:\TeamViewerV.2
kubectl apply -f kubernetes_configmap.yaml
cd D:\TeamViewerV.2\EurekaServer
kubectl apply -f deploy.yaml

cd D:\TeamViewerV.2\BoardServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\ChatServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\EurekaServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\ExecuteServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\paintserver
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\RoomServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\SignalingServer_nodejs
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\SourceServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\UserServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\WebServer
kubectl apply -f deploy.yaml
cd D:\TeamViewerV.2\ZuulServer
kubectl apply -f deploy.yaml


cd D:\TeamViewerV.2\hwa\calendarserver
kubectl apply -f Deployment.yaml
kubectl apply -f Service.yaml

cd D:\TeamViewerV.2\hwa\DatabaseServer
kubectl apply -f Deployment.yaml
kubectl apply -f Service.yaml
