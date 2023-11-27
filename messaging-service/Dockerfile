FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /messaging-service
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG TARGET=/messaging-service/target
COPY --from=build ${TARGET}/messaging-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5454
CMD ["java","-Xmx128m","-jar","app.jar"]