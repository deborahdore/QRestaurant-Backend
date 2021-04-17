FROM maven:latest as builder
COPY pom.xml /usr/local/pom.xml
COPY src /usr/local/src
WORKDIR /usr/local/
RUN mvn clean package

FROM openjdk:11
COPY --from=builder /usr/local/target/qrestaurant-0.0.1-SNAPSHOT.jar qrestaurant.jar
ENTRYPOINT exec java -jar /qrestaurant.jar

