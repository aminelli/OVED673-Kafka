package com.course.kafka.producer;

import com.course.kafka.admin.KafkaAdmins;
import com.course.kafka.partitioner.CustomPartitioner;
import com.course.kafka.utils.Utils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ProducerTestPartitioner {


    public void sendMessages(
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
        props.put(ProducerConfig.ACKS_CONFIG, "0");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class);

        // LOGICA DI CREAZIONE PRODUCER E INVIO MESSAGGI

        KafkaAdmins.createTopic(topicName, partitions, replications, props);


        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = null;

        Date startDate = new Date();

        try {

            for (int count = 0; count < totalMessages; count++) {
                String key = null;

                if (count >= 50 && count < 150) {
                    key = "X" + count;
                } else {
                    key = (count % 2 == 0 ? "K" : "Q") + count;
                }

                String headData = "MSG" + count;
                record = new ProducerRecord<>(topicName, key, "Messaggio nr " + count + " del " + formatter.format(new Date()));
                record.headers().add("CORSO_DATA", headData.getBytes());

                producer.send(record);
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
