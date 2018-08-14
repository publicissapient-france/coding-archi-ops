FROM maven:3.5-jdk-8-alpine as build
ADD pom.xml ./pom.xml
ADD src ./src
RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine
COPY --from=build ./target/ca-ops-referentiel-*.jar referentiel.jar
VOLUME /tmp
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/referentiel.jar"]
