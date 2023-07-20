FROM eclipse-temurin:20-alpine
WORKDIR /opt
ENV PORT 8100
EXPOSE 8100
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar