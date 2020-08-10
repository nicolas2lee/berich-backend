Create Kubernetes configmap from files

    kubectl create configmap name --from-file path/to/file.properties --from-file path/to/file2.properties
    
Example

    kubectl create configmap test-configmap --from-file application.yml --from-file path/to/file2.properties
   
