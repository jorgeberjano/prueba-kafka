package com.at.pruebas;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.LongDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

/**
 *
 * @author jberjano
 */
@Component
public class ProductorConsumidor {
    
    public static final String MI_TOPIC = "mi-primer-topic";
    
    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;

    public void crearProductor() {
        Properties properties = new Properties();
        // Set the brokers (bootstrap servers)
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Configuracion.MI_SERVIDOR);
        // Set how to serialize key/value pairs
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());//"org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
    }

    public void producirMensajes() {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                producer.send(new ProducerRecord<>("mi-primer-topic", "mensaje " + i));

            }
        }).start();
    }

    public void crearConsumidor() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Configuracion.MI_SERVIDOR);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create the consumer using props.
        consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(MI_TOPIC));
    }
   
    public void consumirMensajes() {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord record : records) {
                    System.out.println(record.value());
                }                
            }
        }).start();
    }
}
