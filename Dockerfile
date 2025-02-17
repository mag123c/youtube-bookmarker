# Build stage
FROM bellsoft/liberica-openjdk-alpine:17 AS builder
WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# Run stage
FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
COPY src/main/resources/application-production.yml /app/config/application-production.yml
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
