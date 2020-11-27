package com.at.pruebas;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 *
 * @author jberjano
 */
@Configuration
public class Configuracion {
    
    public static final String MI_SERVIDOR = "192.168.1.94:9092";

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, MI_SERVIDOR);
    return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic miPrimerTopic() {
        return TopicBuilder.name(ProductorConsumidor.MI_TOPIC)
                .partitions(6)
                .replicas(3)
                .compact()
                .build();
    }

}
