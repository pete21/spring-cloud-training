FROM openjdk:11.0.11-jre-slim-buster
ADD target/configuration-server.jar /
CMD java -jar \
    -Dspring.cloud.config.server.git.uri=$GIT_URI \
    -Dspring.cloud.stream.kafka.binder.brokers=$KAFKA_BROKER \
    configuration-server.jar