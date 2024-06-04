FROM eclipse-temurin:11-jdk-alpine as build
WORKDIR /media-api
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN rm src/main/resources/application.yaml
RUN mv src/main/resources/application.yaml_prod src/main/resources/application.yam
RUN apk add maven

RUN apt-get -y update && apt-get -y upgrade && apt-get install -y --no-install-recommends ffmpeg
# RUN sudo apt install subversion
# RUN sudo svn co https://svn.code.sf.net/p/gpac/code/trunk/gpac gpac
# RUN sudo cd gpac
# RUN sudo ./configure --disable-opengl --use-js=no --use-ft=no --use-jpeg=no --use-png=no --use-faad=no --use-mad=no --use-xvid=no --use-vorbis=no --use-ffmpeg=no --use-ogg=no --use-theora=no --use-openjpeg=no
# RUN sudo make
# RUN sudo make install
# RUN sudo cp bin/gcc/libgpac.so /usr/lib
RUN apt install curl gpac -y

RUN --mount=type=cache,target=/root/.m2 mvn clean install -DskipTests

FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
ARG TARGET=/media-api/target
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV EUREKA_URL=${EUREKA_URL}
ENV KAFKA_URL=${KAFKA_URL}
ENV POSTGRES_URL=${POSTGRES_URL}
COPY --from=build ${TARGET}/media-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5252
CMD ["java","-Xmx512m","-jar","app.jar"]