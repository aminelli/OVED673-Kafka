@echo off


docker run -d --name postgres --hostname postgre --network kafka-cluster_net-kafka-cluster -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -v ./postgres/custom-config.conf:/etc/postgresql/postgresql.conf debezium/postgres:12 postgres -c config_file=/etc/postgresql/postgresql.conf

docker run -d --name mongo --hostname mongo --network kafka-cluster_net-kafka-cluster -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -e MONGO_REPLICA_SET_NAME=my-replica-set mongo:4.2.5

docker run -d --name elastic --hostname elastic --network kafka-cluster_net-kafka-cluster -p 9200:9200 -p 9300:9300 -e discovery.type=single-node elasticsearch:7.6.2


docker exec -it -u root ksqldb-server /bin/bash
dnf update -y
dnf install nano -y
cd /usr/share
mkdir kafka

nano /etc/environment
KSQL_CONNECT_PLUGIN_PATH=/usr/share/kafka/confluent-hub-components
KSQL_CONNECT_CONFIG_STORAGE_TOPIC=_ksql-connect-configs
KSQL_CONNECT_OFFSET_STORAGE_TOPIC=_ksql-connect-offsets
KSQL_CONNECT_STATUS_STORAGE_TOPIC=_ksql-connect-statuses
KSQL_CONNECT_GROUP_ID=ksql-connect-cluster
KSQL_CONNECT_BOOTSTRAP_SERVERS=broker:9092
KSQL_CONNECT_KEY_CONVERTER=org.apache.kafka.connect.storage.StringConverter
KSQL_CONNECT_VALUE_CONVERTER=io.confluent.connect.avro.AvroConverter
KSQL_CONNECT_VALUE_CONVERTER_SCHEMA_REGISTRY_URL=http://schema-registry:8081


docker cp confluent-hub-components ksqldb-server:/usr/share/kafka
