# Importer service
[![Github Actions](https://github.com/sergiopoliveira/importer/workflows/master/badge.svg)](https://github.com/sergiopoliveira/importer/actions)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5b035dd9a35c469185fb27fe798fc365)](https://www.codacy.com/app/sergiopoliveira/importer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sergiopoliveira/importer&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/sergiopoliveira/importer/branch/master/graph/badge.svg)](https://codecov.io/gh/sergiopoliveira/importer)


Importer service on startup will check for dataExample.csv and start parsing it to JSON, sending each row at a time to a message queue (ActiveMQ).

To be used together with the Aggregator service.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

JDK 11 and Maven or Docker is required to run this project.

### Build and Run

#### Maven

Build and run the project with Maven:

```
mvn package
mvn spring-boot:run
```

#### Docker

Use the following docker-compose to run the full project:

``` 
version: '3'
services:
  activemq:
    container_name: activemq
    image: vromero/activemq-artemis
    ports:
      - "8161:8161"
      - "61616:61616"
  importer:
    image: sergiopoliveira/importer
    ports:
      - "5050:5050"
    depends_on:
      - activemq
  aggregator:
     image: sergiopoliveira/aggregator
     ports:
      - "5051:5051"
     depends_on:
      - activemq
  zipkin:
     image: openzipkin/zipkin
     ports:
      - "9411:9411"
```

## Running the tests

Tests created using JUnit and Mockito. Run them as:

```
mvn test
```

## Built With

*   Java 11
*   Spring Boot
*   Spring Data JPA
*   ActiveMQ
*   MapStruct
*   Maven
*   JUnit, Mockito
*   H2 in-memory database

## Authors

*   **Sérgio Oliveira** - [sergiopoliveira](https://github.com/sergiopoliveira)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
