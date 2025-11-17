

docker pull apache/kafka:4.1.0

# Creo una rete docker di tipo bridge dedicata alle instanze kafka
docker network create net-kafka

# Creo un'istanza kafka
docker run -d --name broker-kafka --hostname broker-kafka --network net-kafka -p 9092:9092 apache/kafka:4.1.0