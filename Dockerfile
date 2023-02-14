FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp
ARG JAR_FILE=ws-assembly-vote-manager-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar ${0} ${@}"]