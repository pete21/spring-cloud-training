./mvnw clean package -DskipTests=true
docker build -t training/admin-server admin-server
docker build -t training/configuration-server configuration-server
docker build -t training/discovery-server discovery-server
docker build -t training/gateway-server gateway-server
docker build -t training/payments-broker payments-broker
docker build -t training/payments-service payments-service
docker build -t training/shop-service shop-service
