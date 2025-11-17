package com.course.kafka.producer;

import com.course.kafka.admin.KafkaAdmins;
import com.course.kafka.base.ProducerBase;
import com.course.kafka.utils.Utils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerAckOne extends ProducerBase {

    // Ack = 1 e Sync
    public void sendMessagesSync(
            String topicName,
            long totalMessages,
            final int partitions,
            final short replications
    ) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // PREPAZIONE PARAMETRI DI CONFIGURAZIONE

        Properties props = new Properties();

        // Endpoints dei nodi del cluster
        //props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");

        // Client ID
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "PRD-001");

        // Algoritmo di compressione
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

        // TIPO ACK
        props.put(ProducerConfig.ACKS_CONFIG, "1");

        // Full package name delle classi da utilizzare per le serializzazioni delle chiavi e corrispettivi valori
        // props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // LOGICA DI CREAZIONE PRODUCER E INVIO MESSAGGI

        KafkaAdmins.createTopic(topicName, partitions, replications, props);


        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = null;

        Date startDate = new Date();

        try {

            for (int count = 0; count < totalMessages; count++) {
                String key = "K" + count;
                String headData = "MSG" + count;
                record = new ProducerRecord<>(topicName, key, "Messaggio nr " + count + " del " + formatter.format(new Date()));
                record.headers().add("CORSO_DATA", headData.getBytes());

                // RecordMetadata recordMetadata = producer.send(record).get();
                // equivalente a:
                Future<RecordMetadata> future = producer.send(record);
                RecordMetadata recordMetadata = future.get();

                //printMetadata(recordMetadata);
                //System.out.println("Sent message : " + key);
            }
        } catch (Exception e) {
            System.out.println("Producer Error");
        }

        Date endDate = new Date();

        Utils.printDateDiff(startDate, endDate, formatter);

        producer.flush();
        producer.close();

    }

    public void sendMessagesAsync(String topicName, long totalMessages) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // PREPAZIONE PARAMETRI DI CONFIGURAZIONE

        Properties props = new Properties();

        // Endpoints dei nodi del cluster
        //props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");

        // Client ID
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "PRD-001");

        // Algoritmo di compressione
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

        // TIPO ACK
        props.put(ProducerConfig.ACKS_CONFIG, "0");

        // Full package name delle classi da utilizzare per le serializzazioni delle chiavi e corrispettivi valori
        // props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // LOGICA DI CREAZIONE PRODUCER E INVIO MESSAGGI

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = null;

        Date startDate = new Date();

        try {

            for (int count = 0; count < totalMessages; count++) {
                String key = "K" + count;
                String headData = "MSG" + count;
                record = new ProducerRecord<>(topicName, key, "Messaggio nr " + count + " del " + formatter.format(new Date()));
                record.headers().add("CORSO_DATA", headData.getBytes());
                producer.send(record);

                //System.out.println("Sent message : " + key);
            }
        } catch (Exception e) {
            System.out.println("Producer Error");
        }

        Date endDate = new Date();

        Utils.printDateDiff(startDate, endDate, formatter);

        producer.flush();
        producer.close();

    }

}
