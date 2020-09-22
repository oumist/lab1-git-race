FROM openjdk:11

# Copy src files to container
WORKDIR /git-race
COPY src src/
COPY gradle gradle/
COPY build.gradle gradlew gradlew.bat ./

# Build 
RUN "./gradlew" "build"

# Run
ENTRYPOINT [ "./gradlew", "bootRun" ]