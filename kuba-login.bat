kubectl config delete-cluster cooperatool-kube
kubectl config delete-context cooperatool-kube-admin
az aks get-credentials --resource-group cooperatool-rg --name cooperatool-kube --admin
az aks update -n cooperatool-kube -g cooperatool-rg --attach-acr cooperatoolcr