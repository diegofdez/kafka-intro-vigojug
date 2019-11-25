package com.vigojug.demo2;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {
	private static final Logger log = LoggerFactory.getLogger(ConsumerApplication.class);
	
	private static final String TOPIC = "vigojug-demo2";
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
		
		Properties kafkaProps = new Properties();
		kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
		kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "vigojug");
		kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProps);
		
		consumer.subscribe(Collections.singletonList(TOPIC));
		
		try {
			while (true) {
			    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			    log.info("Got {} messages from poll", records.count());
			    for (ConsumerRecord<String, String> record : records) {
			      log.info("Got message: key={}, value={}", record.key(), record.value());
			    }

			}
		} finally {
			consumer.close();
		}
	}

}
