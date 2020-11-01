FROM openjdk:11-jre
VOLUME /tmp
COPY target/*.jar /opt/app.jar
ENTRYPOINT [ "java", "-jar", "/opt/app.jar" ]
CMD []
