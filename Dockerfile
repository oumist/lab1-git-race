#https://codefresh.io/docs/docs/learn-by-example/java/gradle/
FROM gradle:6.6.1-jdk11 AS build
COPY --chown=gradle:gradle . /usr/src/git-race
WORKDIR /usr/src/git-race
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /ap
COPY --from=build /usr/src/git-race/build/libs/lab1-git-race.war /app/lab1-git-race.war
ENTRYPOINT ["java"] 
#, "-jar", "/app/lab1-git-race.war"]
CMD ["-jar", "/app/lab1-git-race.war"]
