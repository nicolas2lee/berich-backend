# MicroK8S
## Get auth token
token=$(microk8s kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)
microk8s kubectl -n kube-system describe secret $token

## Forward port 
microk8s kubectl port-forward -n kube-system service/kubernetes-dashboard 10443:443

## Configmap
Create Kubernetes configmap from files

    kubectl create configmap name --from-file path/to/file.properties --from-file path/to/file2.properties
    
Example

        kubectl create configmap test-configmap --from-file application.yml --from-file application-prod.yml logback.xml
   
## Secret
### Password
opaque base 64
    
    echo -n '<password>' | base64
