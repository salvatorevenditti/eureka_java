# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# Generate WAR
CMD ["mvn clean install"]
# copy WAR into image
COPY target/eureka-0.0.1-SNAPSHOT.war /app.war
# run application with this command line
CMD ["/usr/bin/java", "-jar", "-dockerDspring.profillses.active=default", "/app.war"]