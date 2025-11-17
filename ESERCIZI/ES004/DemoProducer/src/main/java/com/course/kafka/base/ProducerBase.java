package com.course.kafka.base;

import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerBase {

    public void printMetadata(RecordMetadata data) {
        System.out.println(data.toString());
    }


}
