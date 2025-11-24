# Esercizio Debezium MySQL


```sh
docker pull quay.io/debezium/example-mysql:3.3
docker pull mysql:8.2

docker run -it --rm --name mysql --hostname mysql --network kafka-cluster_net-kafka-cluster -p 3306:3306 -e MYSQL_ROOT_PASSWORD=debezium -e MYSQL_USER=mysqluser -e MYSQL_PASSWORD=mysqlpw quay.io/debezium/example-mysql:3.3

docker run -it --rm --name mysqlterm --hostname mysqlterm --network kafka-cluster_net-kafka-cluster --link mysql mysql:8.2 sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -umysqluser -p"mysqlpw"'



```