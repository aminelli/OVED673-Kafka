# Esercizio Debezium MySQL


```sh
docker pull quay.io/debezium/example-mysql:3.3
docker pull mysql:8.2

docker run -it --rm --name mysql --hostname mysql --network kafka-cluster_net-kafka-cluster -p 3306:3306 -e MYSQL_ROOT_PASSWORD=debezium -e MYSQL_USER=mysqluser -e MYSQL_PASSWORD=mysqlpw quay.io/debezium/example-mysql:3.3

docker run -it --rm --name mysqlterm --hostname mysqlterm --network kafka-cluster_net-kafka-cluster mysql:8.2 sh -c 'exec mysql -h"mysql" -P"3306" -umysqluser -p"mysqlpw"'


docker run -it --rm --name connect --hostname connect --network kafka-cluster_net-kafka-cluster -p 8083:8083 -e GROUP_ID=1 -e CONFIG_STORAGE_TOPIC=my_connect_configs -e OFFSET_STORAGE_TOPIC=my_connect_offsets -e STATUS_STORAGE_TOPIC=my_connect_statuses --link broker01:kafka --link mysql:mysql quay.io/debezium/connect:3.3
```