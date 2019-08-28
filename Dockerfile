FROM maven:3.6.1-jdk-11

COPY . /usr/app/

WORKDIR /usr/app

RUN mvn package

CMD ["java", "-jar", "target/importer-0.0.1-SNAPSHOT.jar"]