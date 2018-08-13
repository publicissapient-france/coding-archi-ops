FROM openjdk:8-jre-alpine
COPY /target/ca-ops-referentiel-*.jar /referentiel.jar
VOLUME /tmp
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/referentiel.jar"]
