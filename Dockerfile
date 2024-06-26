# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.8.3-amazoncorretto-17 as corretto-jdk
# copy the pom and src code to the container
COPY ./ ./
# package our application code
RUN mvn -Dmaven.test.skip=true -Dspring.profiles.active=production  clean package
# the second stage of our build will use open jdk 8 on alpine 3.9
FROM amazoncorretto:17.0.3-alpine
# copy only the artifacts we need from the first stage and discard the rest
COPY --from=corretto-jdk ./target/blacklist-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
# set the startup command to execute the jar
CMD ["java", "-jar", "/app.jar"]