FROM maven:3.6.3-adoptopenjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package

FROM adoptopenjdk:11-jre-hotspot
ENV JAVA_OPTS=""
WORKDIR /app
COPY --from=builder /app/target/gateway-1.0-SNAPSHOT.jar app.jar
VOLUME /conf
CMD java -jar app.jar
