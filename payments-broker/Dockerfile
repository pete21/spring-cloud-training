FROM openjdk:11.0.11-jre-slim-buster
ADD target/payments-broker.jar /
CMD java -jar \
    -Dspring.profiles.active=$ACTIVE_PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    payments-broker.jar