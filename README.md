# Aggregator service

[![CircleCI](https://circleci.com/gh/sergiopoliveira/aggregator.svg?style=svg)](https://circleci.com/gh/sergiopoliveira/aggregator)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c05d2ac0b22047a9b5c03408a8ec8f9d)](https://www.codacy.com/app/sergiopoliveira/aggregator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sergiopoliveira/aggregator&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/sergiopoliveira/aggregator/branch/master/graph/badge.svg)](https://codecov.io/gh/sergiopoliveira/aggregator)

Aggregator service will to listen to a Message Queue, parse the JSON messages to Products and and saves them to the database. Also tracks if the Product is newly added or updated.

To be used together with the Importer service.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

JDK 8 and Maven or Docker is required to run this project.

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
```

## REST Endpoints

```
http://localhost:5051/api/v1/products -> List all products in the database
http://localhost:5051/api/v1/statistics/daily -> Get daily statistics for Products inserted or updated
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
