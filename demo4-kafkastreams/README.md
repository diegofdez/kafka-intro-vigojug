Simple example of Kafka Streams

# Create test topics before running the app

**Linux**

```
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic vigojugstream-input-topic --partitions 1 --replication-factor 1
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic vigojugstream-output-topic --partitions 1 --replication-factor 1
```

**Windows**

```
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --create --topic vigojugstream-input-topic --partitions 1 --replication-factor 1
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --create --topic vigojugstream-output-topic --partitions 1 --replication-factor 1
```

# Prepare producer and consumer on topics

**Linux**

```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic vigojugstream-input-topic
```

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic vigojugstream-output-topic --from-beginning
```

**Windows**

```
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic vigojugstream-input-topic
```

```
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic vigojugstream-output-topic --from-beginning
```