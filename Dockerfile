FROM eclipse-temurin:22-jdk-alpine AS build
WORKDIR /workspace/app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:22-jdk-alpine
VOLUME /tmp
ARG VERSION=0.0.1-SNAPSHOT
ARG NAME=le_phare_culturel
COPY --from=build /workspace/app/target/$NAME-$VERSION.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
