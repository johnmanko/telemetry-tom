![header image](./header.webp "Telemetry Tom Header")
# Telemetry Tom 🤩

♫ Ground Control to Metrics Tom. ♫

(a nod to David Bowie) 

## Services

### `springboot-datadog-telemetry-tom`

#### Run service standalone

This service should be able to publish metrics directly to Datadog without a Datadog Agent.

Uncomment the following `application.properties` and set the values for `api-key` and `application-key`, and update `uri` to your provided Datadog endpoint URI:

```
management.datadog.metrics.export.enabled=true
management.datadog.metrics.export.uri=https://api.us5.datadoghq.com
management.datadog.metrics.export.api-key=
management.datadog.metrics.export.application-key=
management.datadog.metrics.export.step=10s
management.datadog.metrics.export.description=Springboot Datadog Telemetry Tom
management.datadog.metrics.export.host-tag=springboot-datadog-telemetry-tom
```

Launch service:
```shell
./mvnw spring-boot:run
```

## Docker Compose

### Run services using Docker Compose

Leave `application.properties` in project as-is, and create an `.env` from `sample.env` in the root of this repo.

```
DATADOG_API_KEY=xxxx9999xxxx9999xxxx9999xxxx9999
DATADOG_SITE="us1.datadoghq.com"
DATADOG_APP_KEY=
DATADOG_STEP=10s
```

Launch Docker compose:

```shell
docker compose up --build --force-recreate
```

## Test

A feeble attempt at stress testing (ie, sync calls).

```shell
./stress-test.sh
```