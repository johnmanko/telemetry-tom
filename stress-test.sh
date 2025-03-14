#!/bin/sh

for i in {1..1000}
do
    curl localhost:8081/api/hello # Spring Boot with Datadog Direct
    #curl localhost:8082/api/hello # Spring Boot with Open Telemetry + Datadog Agent
    sleep 0.5  # Sleep for 500 milliseconds
done

