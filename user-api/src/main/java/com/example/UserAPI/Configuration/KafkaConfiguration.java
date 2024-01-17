package com.example.UserAPI.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaUrl;
    
    @Bean
    KafkaTemplate<String, String> createKafkaTemplate() {
        
        Map<String, Object> producerConfigs = new HashMap<>();
        producerConfigs.put("bootstrap.servers", kafkaUrl);
        producerConfigs.put("key.serializer", StringSerializer.class.getName());
        producerConfigs.put("value.serializer", StringSerializer.class.getName());
        
        DefaultKafkaProducerFactory<String, String> producerFactory =
            new DefaultKafkaProducerFactory<>(producerConfigs);

        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    NewTopic userCreatedTopic() {
        return new NewTopic("user-created", 1, (short) 1);
    }
}
