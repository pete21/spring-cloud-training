FROM openjdk:11.0.11-jre-slim-buster
ADD target/gateway-server.jar /
CMD java -jar \
    -Dspring.profiles.active=$ACTIVE_PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    gateway-server.jar