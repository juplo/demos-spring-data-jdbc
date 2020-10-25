FROM openjdk:8-jre-alpine
VOLUME /tmp
COPY target/*.jar /opt/app.jar
ENTRYPOINT [ "/usr/bin/java", "-jar", "/opt/app.jar" ]
CMD []
