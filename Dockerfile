FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=service/build/libs/service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]