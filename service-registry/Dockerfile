FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /api-gateway
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
#RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG TARGET=/api-gateway/target
COPY --from=build ${TARGET}/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5050
CMD ["java","-Xmx128m","-jar","app.jar"]