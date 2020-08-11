FROM maven:3.6.3-adoptopenjdk-11 AS builder
WORKDIR /app
COPY . .
RUN mvn -e -B clean install

FROM adoptopenjdk:11-jre-hotspot
ENV JAVA_OPTS=""
WORKDIR /app
COPY --from=builder /app/exposition/target/exposition-1.0-SNAPSHOT.jar app.jar
VOLUME /conf
CMD java -Dspring.config.location=/conf/ -jar app.jar
