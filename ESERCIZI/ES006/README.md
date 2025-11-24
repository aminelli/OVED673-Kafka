# Esercizio Debezium MySQL


```sh
docker pull quay.io/debezium/example-mysql:3.3
docker pull mysql:8.2

docker run -it --rm --name mysql --hostname mysql --network kafka-cluster_net-kafka-cluster -p 3306:3306 -e MYSQL_ROOT_PASSWORD=debezium -e MYSQL_USER=mysqluser -e MYSQL_PASSWORD=mysqlpw quay.io/debezium/example-mysql:3.3

docker run -it --rm --name mysqlterm --hostname mysqlterm --network kafka-cluster_net-kafka-cluster mysql:8.2 sh -c 'exec mysql -h"mysql" -P"3306" -umysqluser -p"mysqlpw"'

docker run -it --rm --name connect --hostname connect --network kafka-cluster_net-kafka-cluster -p 8083:8083 -e GROUP_ID=1 -e BOOTSTRAP_SERVERS=broker01:29092,broker02:29092 -e CONFIG_STORAGE_TOPIC=my_connect_configs -e OFFSET_STORAGE_TOPIC=my_connect_offsets -e STATUS_STORAGE_TOPIC=my_connect_statuses  quay.io/debezium/connect:3.3

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '{ "name": "inventory-connector", "config": { "connector.class": "io.debezium.connector.mysql.MySqlConnector", "tasks.max": "1", "database.hostname": "mysql", "database.port": "3306", "database.user": "debezium", "database.password": "dbz", "database.server.id": "184054", "topic.prefix": "dbserver1", "database.include.list": "inventory", "schema.history.internal.kafka.bootstrap.servers": "broker01:29092,broker02:29092", "schema.history.internal.kafka.topic": "schemahistory.inventory" } }'

curl -H "Accept:application/json" localhost:8083/connectors/
curl -i -X GET -H "Accept:application/json" localhost:8083/connectors/inventory-connector

```