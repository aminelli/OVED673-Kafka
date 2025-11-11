# Esercizio 1

```sh
# Come generare un UUID per un Cluster
bin\windows\kafka-storage.bat random-uuid
# ID Generato: 0y2X7NWETBKqeNI8Y5ixxg
# Esempio di un ID generico: CLST_CORSO

# Format Log Directories
# syntax: bin\windows\kafka-storage.bat format -t [CLUSTER ID] -c config\server.properties
bin\windows\kafka-storage.bat format --standalone -t 0y2X7NWETBKqeNI8Y5ixxg -c config\server.properties


# Start Kafka Server (Brocker standalone)
bin\windows\kafka-server-start.bat config\server.properties

# Creazione Topic
bin\windows\kafka-topics.bat --create --topic corso-test --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

# Recupera info sul topic
bin\windows\kafka-topics.bat --describe --topic corso-test --bootstrap-server localhost:9092    

# Aprire un nuovo terminale, spostarsi sulla cartella di Kafka ed eseguire il seguente comando per creare un producer:
bin\windows\kafka-console-producer.bat --topic corso-test --bootstrap-server localhost:9092

# Aprire un nuovo terminale, spostarsi sulla cartella di Kafka ed eseguire il seguente comando per creare un consumer:
bin\windows\kafka-console-consumer.bat --topic corso-test --bootstrap-server localhost:9092 --from-beginning

```


```sh
# COMANDI UTILI

# Entrare nel container con processo root
docker exec -it --user root broker-kafka /bin/bash

# Nel container lanciare:
apk update
apk add htop


```