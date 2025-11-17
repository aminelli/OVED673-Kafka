package com.course.kafka.base;

import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerBase {

    public void printMetadata(RecordMetadata data) {

        System.out.println("\n Record Data:");
        System.out.println("Topic      : "  + data.topic());
        System.out.println("Partizione : "  + data.partition());
        System.out.println("Offset     : "  + data.offset());
        System.out.println("Timestamp  : "  + data.timestamp());

    }


}
