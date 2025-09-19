FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/cooking-recipes-api-1.0.0.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]

#  java -jar app.jar