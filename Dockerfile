FROM openjdk:17.0.2-jdk-slim-bullseye
RUN adduser --system --group scrada
USER scrada:scrada
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
CMD ["smashggApiKey", "telemgramChatID", "telegramBotKey"]