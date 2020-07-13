# berich-backend

## aws
### s3 client examples
https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/s3/src/main/java/com/example/s3/S3ObjectOperations.java

### netty with openssl
https://netty.io/wiki/requirements-for-4.x.html#tls-with-openssl

### deploy to ec2
 
    sudo yum remove java-1.7.0-openjdk
    
    sudo rpm --import https://yum.corretto.aws/corretto.key 
    sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
    
    sudo yum install -y java-11-amazon-corretto-devel
    
    ssh -i "berich-ec2-keypair.pem" ec2-user@ec2-35-180-39-209.eu-west-3.compute.amazonaws.com
    
    scp -i berich-ec2-keypair.pem /Users/xinrui/tao/apps/github/berich-backend/micro-service/exposition/target/exposition-1.0-SNAPSHOT.jar ec2-user@ec2-35-180-39-209.eu-west-3.compute.amazonaws.com:~
   
    scp -i berich-ec2-keypair.pem /Users/xinrui/tao/apps/github/berich-backend/micro-service/exposition/src/main/resources/application-prod.yaml ec2-user@ec2-35-180-39-209.eu-west-3.compute.amazonaws.com:~

### Command to run app

    java -jar -Dspring.profiles.active=prod -Dspring.config.location=./ exposition-1.0-SNAPSHOT.jar &
    
### url

http://ec2-35-180-39-209.eu-west-3.compute.amazonaws.com:8080
    
## Reference
* Installation jdk https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/generic-linux-install.html 

## ibmcloud
ibmcloud cr build -t de.icr.io/berich/fund-service .    
    