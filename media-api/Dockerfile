FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /media-service
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
#RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG TARGET=/media-service/target
COPY --from=build ${TARGET}/media-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5353
CMD ["java","-Xmx256m","-jar","app.jar"]