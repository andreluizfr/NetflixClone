package com.example.UserAPI.Util;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaUtil <T> {
    
    public KafkaTemplate<String, T> createKafkaTemplate() {
        
        Map<String, Object> producerConfigs = new HashMap<>();
        producerConfigs.put("bootstrap.servers", "localhost:9092");
        producerConfigs.put("key.serializer", StringSerializer.class.getName());
        producerConfigs.put("value.serializer", StringSerializer.class.getName());
        
        DefaultKafkaProducerFactory<String, T> producerFactory =
            new DefaultKafkaProducerFactory<>(producerConfigs);

        return new KafkaTemplate<>(producerFactory);
    }
}
