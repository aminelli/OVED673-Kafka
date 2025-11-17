package com.course.kafka.partiotioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        if (key != null && key instanceof String) {

            String keyString = (String) key;
            if (keyString.startsWith("K")) {
                return 0;
            } else if (keyString.startsWith("Q")) {
                return 1;
            } else {
                // CASO X
                return 2;
            }
        }
        return 2;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
