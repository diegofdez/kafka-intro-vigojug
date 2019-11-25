package com.vigojug.demo2.partitioner;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPartitioner extends DefaultPartitioner {
	private static final Logger log = LoggerFactory.getLogger(CustomPartitioner.class);
	
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		log.info("Should assign partition for topic={}, key={}", topic, key);
		return super.partition(topic, null, null, value, valueBytes, cluster);
	}
}
