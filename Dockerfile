FROM openjdk:11-jdk as build
 
WORKDIR /srv
 
COPY gradlew gradlew.bat ./
 
COPY gradle/ gradle/
 
RUN ./gradlew
 
COPY settings.gradle build.gradle gradle.properties ./

RUN ./gradlew build
 
COPY src/ src/

RUN ./gradlew distTar && rm gradle.properties

FROM openjdk:11-jre

RUN useradd -m app
RUN chown app:app /srv
USER app

WORKDIR /srv

COPY --from=build --chown=app /srv/build/distributions/java-template.tar ./java-template.tar
COPY config.yaml ./

RUN tar -xvf java-template.tar

CMD java-template/bin/java-template server config.yaml
 
EXPOSE 8080
