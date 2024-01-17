package com.example.MediaAPI.Util;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaUtil <T> {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaUrl;
    
    public KafkaTemplate<String, T> createKafkaTemplate() {
        
        Map<String, Object> producerConfigs = new HashMap<>();
        producerConfigs.put("bootstrap.servers", kafkaUrl);
        producerConfigs.put("key.serializer", StringSerializer.class.getName());
        producerConfigs.put("value.serializer", StringSerializer.class.getName());
        
        DefaultKafkaProducerFactory<String, T> producerFactory =
            new DefaultKafkaProducerFactory<>(producerConfigs);

        return new KafkaTemplate<>(producerFactory);
    }
}
