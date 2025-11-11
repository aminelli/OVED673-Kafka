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

```