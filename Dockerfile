# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package

# ---- Run stage ----
FROM eclipse-temurin:17-jdk
WORKDIR /app

# copy the built jar
COPY --from=build /app/target/Doctor-Appoinment-System-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-Dserver.port=${PORT}","-jar","app.jar"]
