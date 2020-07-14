helm repo add nicolas2lee https://nicolas2lee.github.io/java-springboot-helm-chart/
helm repo update

helm upgrade  --install -f ./k8s/fund-service-values.yaml berich-fund-service nicolas2lee/javaspringboot