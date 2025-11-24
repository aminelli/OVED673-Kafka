@echo off


docker run -d --name postgres --hostname postgre --network kafka-cluster_net-kafka-cluster -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -v ./postgres/custom-config.conf:/etc/postgresql/postgresql.conf debezium/postgres:12 postgres -c config_file=/etc/postgresql/postgresql.conf

