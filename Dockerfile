#Step 1: Compile
FROM gradle:6.6.1-jdk11 AS build
COPY --chown=gradle:gradle . /usr/src/git-race
WORKDIR /usr/src/git-race
RUN gradle build --no-daemon 

#Step 2: Run image
FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /usr/src/git-race/build/libs/lab1-git-race.war /app/lab1-git-race.war
ENTRYPOINT ["java"]
CMD ["-jar", "/app/lab1-git-race.war"]

#################################################################################
#Another version of Dockerfile
#################################################################################
# FROM openjdk:11

# # Copy src files to container
# WORKDIR /git-race
# COPY src src/
# COPY gradle gradle/
# COPY build.gradle gradlew gradlew.bat ./

# # Build 
# RUN "./gradlew" "build"

# # Run
# ENTRYPOINT [ "./gradlew", "bootRun" ]

