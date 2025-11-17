package com.course.kafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Properties;

public class ProducerFireAndForget {

    public void sendMessages(String topicName, long totalMessages) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Properties props = new Properties();

        // Endpoints dei nodi del cluster
        //props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");

        // Client ID
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"PRD-001");

        // Algoritmo di compressione
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"gzip");

        // TIPO ACK
        props.put(ProducerConfig.ACKS_CONFIG, "0");


    }

}
