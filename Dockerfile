# First stage: build the application
FROM gradle:8.8-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Second stage: run the application
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]