FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /user-service
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
#RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG TARGET=/user-service/target
COPY --from=build ${TARGET}/user-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5252
CMD ["java","-Xmx256m","-jar","app.jar"]