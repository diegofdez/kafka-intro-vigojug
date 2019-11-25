# Run Kafka Connect

**Linux**

```
cd $KAFKA_HOME
bin/connect-distributed.sh config/connect-distributed.properties
```

**Windows**

```
cd %KAFKA_HOME%
bin\windows\connect-distributed.bat config\connect-distributed.properties
```

# Open postman and load "Kafka Connect" collection

Find the collection in this repo:

postman/Kafka Connect.postman_collection.json

# Demo

## Create a source file

The route in Kafka Connect are relative to startup path

```
cd $KAFKA_HOME
mkdir demo
touch  demo/VigoJUG.txt
```

## Create source and sink using postman

## Check that writing on source produces updates in sink
