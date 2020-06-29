helm repo add nicolas2lee https://nicolas2lee.github.io/java-springboot-helm-chart/
helm repo update

helm install  -f ./k8s/gateway-values.yaml berich-gateway nicolas2lee/javaspringboot