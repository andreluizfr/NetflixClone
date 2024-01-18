FROM eclipse-temurin:11-jdk-alpine as build
WORKDIR /api-gateway
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN rm src/main/resources/application.yaml
RUN mv src/main/resources/application.yaml_prod src/main/resources/application.yaml
RUN apk add maven
RUN --mount=type=cache,target=/root/.m2 mvn clean install -DskipTests
#RUN mvn install -DskipTests

FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
ARG TARGET=/api-gateway/target
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV EUREKA_URL=${EUREKA_URL}
ENV KAFKA_URL=${KAFKA_URL}
ENV POSTGRES_URL=${POSTGRES_URL}
COPY --from=build ${TARGET}/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5050
CMD ["java","-Xmx512m","-jar","app.jar"]