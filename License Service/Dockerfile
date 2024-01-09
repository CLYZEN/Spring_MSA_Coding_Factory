# Build stage
FROM openjdk:17 AS build
WORKDIR application
ARG JAR_FILE=build/libs/licensing-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Runtime stage
FROM openjdk:17
WORKDIR application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]
