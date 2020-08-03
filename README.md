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

## Mongodb

mongo 'mongodb://dbaas252.hyperp-dbaas.cloud.ibm.com:28220,dbaas250.hyperp-dbaas.cloud.ibm.com:28026,dbaas251.hyperp-dbaas.cloud.ibm.com:28189/admin?replicaSet=berich' --tls --username admin --tlsCAFile ./cert.pem
    
## API
方法1：HTTP://HQ.SINAJS.CN/LIST=[股票代码]
返回结果：JSON实时数据，以逗号隔开相关数据，数据依次是“股票名称、今日开盘价、昨日收盘价、当前价格、今日最高价、今日最低价、竞买价、竞卖价、成交股数、成交金额、买1手、买1报价、买2手、买2报价、…、买5报价、…、卖5报价、日期、时间”。

获取当前的股票行情，如http://hq.sinajs.cn/list=sh601006，注意新浪区分沪深是以sh和sz区分。


方法3：http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=[市场][股票代码]&scale=[周期]&ma=no&datalen=[长度]

返回结果：获取5、10、30、60分钟JSON数据；day日期、open开盘价、high最高价、low最低价、close收盘价、volume成交量；向前复权的数据。

注意，最多只能获取最近的1023个数据节点。

例如，

http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz002095&scale=60&ma=no&datalen=1023，获取深圳市场002095股票的60分钟数据，获取最近的1023个节点。



keytool -importcert -file /Users/xinrui/tao/apps/github/berich-backend/cert.pem -keystore cacerts -alias "berich-mongo-ibmcloud"