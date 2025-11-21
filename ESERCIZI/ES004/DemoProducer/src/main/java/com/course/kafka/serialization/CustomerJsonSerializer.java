package com.course.kafka.serialization;

import com.course.kafka.model.Customer;
import org.apache.kafka.common.serialization.Serializer;
import tools.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomerJsonSerializer implements Serializer<Customer> {

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Customer data) {


        if (data==null) {
            return new byte[0];
        }

        try {
            //byte[] serJson = data.toJson().getBytes(StandardCharsets.UTF_8);
            //ByteBuffer buffer = ByteBuffer.allocate(serJson.length);
            //buffer.put(serJson);
            //return buffer.array();
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
