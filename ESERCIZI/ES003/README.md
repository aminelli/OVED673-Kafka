# Kafka Cluster

```sh

docker pull confluentinc/cp-server:8.1.0 

docker network create net-kafka

docker volume create vol-broker01
docker volume create vol-broker02
docker volume create vol-broker03

docker run -d --hostname broker01 --name broker01 --network net-kafka -p 9092:9092 -p 9101:9101 --env-file .env.broker01 -v vol-broker01:/klogs/kraft-combined-logs confluentinc/cp-server:8.1.0


```