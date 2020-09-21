FROM gradle:6.6.1-jdk11 AS build
COPY --chown=gradle:gradle . /usr/src/git-race
WORKDIR /usr/src/git-race
RUN gradle build --no-daemon 

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /usr/src/git-race/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]