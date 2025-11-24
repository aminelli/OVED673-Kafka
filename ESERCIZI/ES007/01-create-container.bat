@echo off


docker run -d --name postgres --hostname postgre --network kafka-cluster_net-kafka-cluster -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -v ./postgres/custom-config.conf:/etc/postgresql/postgresql.conf debezium/postgres:12 postgres -c config_file=/etc/postgresql/postgresql.conf

docker run -d --name mongo --hostname mongo --network kafka-cluster_net-kafka-cluster -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -e MONGO_REPLICA_SET_NAME=my-replica-set mongo:4.2.5

docker run -d --name elastic --hostname elastic --network kafka-cluster_net-kafka-cluster -p 9200:9200 -p 9300:9300 -e discovery.type=single-node elasticsearch:7.6.2

REM PROCEDURA PER ENTRARE NEL CONTAINER E MONTARE I CONNECTOR PLUGIN
REM Comando per entrare nel container
docker exec -it -u root ksqldb-server /bin/bash

REM una voltka entrati nel container lanciare i seguenti comandi:
dnf update -y
dnf install nano -y
cd /usr/share
mkdir kafka

nano /etc/profile.d/custom-conf.sh
REM nel file incollare le seguenti righe:
export KSQL_CONNECT_CONFIG_STORAGE_TOPIC=_ksql-connect-configs
export KSQL_CONNECT_OFFSET_STORAGE_TOPIC=_ksql-connect-offsets
export KSQL_CONNECT_STATUS_STORAGE_TOPIC=_ksql-connect-statuses
export KSQL_CONNECT_GROUP_ID=ksql-connect-cluster
export KSQL_CONNECT_BOOTSTRAP_SERVERS=broker01:29092,broker02:29092,broker03:29092
export KSQL_CONNECT_KEY_CONVERTER=org.apache.kafka.connect.storage.StringConverter
export KSQL_CONNECT_VALUE_CONVERTER=io.confluent.connect.avro.AvroConverter
export KSQL_CONNECT_VALUE_CONVERTER_SCHEMA_REGISTRY_URL=http://schema-registry:8081
REM export KSQL_KSQL_CONNECT_URL=
REM Per salvare CTRL+X e poi Y e poi INVIO per salvare

nano /etc/ksqldb/ksql-server.properties

REM una volta usciti dal container, lanciare dalla rott di questo esercizio il comando:
docker cp confluent-hub-components ksqldb-server:/usr/share/java/
