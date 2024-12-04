
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn

COPY src ./src

RUN ./mvnw package -DskipTests -B

FROM eclipse-temurin:17-jre AS runtime

WORKDIR /app


EXPOSE 8080

CMD ["java", "-jar", "app.jar"]