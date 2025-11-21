package com.course.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class ConsumerGeneric {


    public <K, V> void loadRecords(
            String topicName,
            String groupName,
            String clientID,
            Class<? extends Deserializer<K>> keyDeserializer,
            Class<? extends Deserializer<V>> valueDeserializer

    ) {

        Properties props = new Properties();

        // Endpoints dei nodi del cluster
        //props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");

        // Group ID
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);

        // Client ID
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientID);

        // earliest
        // latest
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Disabilita i commit
        //props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // ABILITARE IDEP

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);

        final Consumer<K, V> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));

        //// Logica per reset offset
        // TopicPartition part = new TopicPartition(topicName, 0);
        // consumer.assign(Collections.singletonList(part));
        //// Reset offset all'inizio
        // consumer.seekToBeginning(Collections.singletonList(part));
        //// Reset offset alla fine
        //consumer.seekToEnd(Collections.singletonList(part));
        //// Reset ad un offset specifico
        //consumer.seek(part , 133);



        try {

            while (true) {
                ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<K, V> record : records) {
                    K key = record.key();
                    V value = record.value();

                    // Qui metti logica di businness per elaborare ogni singolo messaggio

                    System.out.printf("Letto record part %d offset %d con key + %s e value %s \n", record.partition(), record.offset(), key, value);
                }

                // commit manuale
                // consumer.commitSync();
                // consumer.commitAsync();


            }

        } catch (Exception e) {
            System.out.println("Errore");
        } finally {
            consumer.close();
        }

    }


}
