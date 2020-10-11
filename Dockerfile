# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# copy WAR into image
COPY docker/eureka-0.0.1-SNAPSHOT.war /app.war
# run application with this command line
CMD ["/usr/bin/java", "-jar", "", "/app.war"]
