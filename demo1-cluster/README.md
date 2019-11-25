# Install Kafka.

https://kafka.apache.org/downloads

# DEMO

## Run Zookeeper

**Linux**

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

**Windows**

```
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

## Run Kafka broker

**Linux**

```
bin/kafka-server-start.sh config/server.properties
```

**Windows**

```
bin\windows\kafka-server-start.bat  config\server.properties
```

## Operations with topics

### List topics

**Linux**

```
bin/kafka-topics.sh --zookeeper localhost:2181 --list
```

**Windows**

```
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --list
```

### Create topic

**Linux**

```
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic vigojug-demo1 --partitions 1 --replication-factor 1
```

**Windows**

```
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --create --topic vigojug-demo1 --partitions 1 --replication-factor 1
```

### Increase number of partitions for a topic

**Linux**

```
bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic vigojug-demo1 --partitions 2
```

**Windows**

```
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --alter --topic vigojug-demo1 --partitions 2
```

### Describe topic

**Linux**

```
bin/kafka-topics.sh --zookeeper localhost:2181 --describe
```

**Windows**

```
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --describe
```

### Delete topic

**Linux**

```
bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic vigojug-demo1
```

**Windows**

```
bin\windows\kafka-topics.bat --zookeeper localhost:2181 --delete --topic vigojug-demo1
```

Delete has some issues in windows as described here: https://stackoverflow.com/questions/50755827/accessdeniedexception-when-deleting-a-topic-on-windows-kafka

## Second Kafka broker

We must set different values from first broker in:
- broker.id
- log.dirs (because we are in the same machine)
- port (because we are in the same machine - DEPRECATED)
- listeners=PLAINTEXT://:{PORT} (recommended instead of _port_ property)

**Linux**

```
bin/kafka-server-start.sh  config/server2.properties
```

**Windows**

```
bin\windows\kafka-server-start.bat  config\server2.properties
```

## Send messages to Kafka

**Linux**

```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic vigojug-demo1
```

**Windows**

```
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic vigojug-demo1
```

## Get messages from Kafka

**Linux**

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic vigojug-demo1 --from-beginning
```

**Windows**


```
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic vigojug-demo1 --from-beginning
```
