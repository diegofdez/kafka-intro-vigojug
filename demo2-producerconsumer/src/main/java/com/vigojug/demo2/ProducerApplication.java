package com.vigojug.demo2;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication {
	private static final Logger log = LoggerFactory.getLogger(ProducerApplication.class);
	
	private static final String TOPIC = "vigojug-demo2";
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
		
		Properties kafkaProps = new Properties();
		kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("partitioner.class", "com.vigojug.demo2.partitioner.CustomPartitioner");
		
		KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
		
		ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "host", Long.toString(System.currentTimeMillis()));
		
		try {
			RecordMetadata metadata = producer.send(record).get();
			log.info("Got metadata: partition={}, offset={}", metadata.partition(), metadata.offset());
		} catch (Exception e) {
			log.error("Got exception publishing to topic ");
		} finally {
			producer.close();
		}
	}

}
