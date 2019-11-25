package com.vigojug.demo4;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamsApplication {
	private static final Logger log = LoggerFactory.getLogger(StreamsApplication.class);
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(StreamsApplication.class, args);
		
		Properties kafkaProps = new Properties();
		kafkaProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
		kafkaProps.put(StreamsConfig.APPLICATION_ID_CONFIG, "vigojugstream");
		kafkaProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		kafkaProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> source = builder.stream("vigojugstream-input-topic");
		
		KStream<String, String> result = source
				.map((k, v) -> toUpperCase(k, v));
		result.to("vigojugstream-output-topic");
		
		KafkaStreams streams = new KafkaStreams(builder.build(), kafkaProps);
		streams.start();
		log.info("Stream running");
		Thread.sleep(60000);
		log.info("Finish stream");
		streams.close();
	}
	
	private static KeyValue<String, String> toUpperCase(String key, String value) {
		String upperKey = (key == null) ? "NULL_KEY" : key.toUpperCase();
		String upperValue = (value == null) ? "NULL_VALUE" : value.toUpperCase();
		
		log.info("Mapped values: {}/{}", upperKey, upperValue);
		
		return new KeyValue<>(upperKey, upperValue);
	}

}
