FROM openjdk:17-jdk-slim AS builder

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew bootJar

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
