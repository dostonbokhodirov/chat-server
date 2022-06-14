FROM openjdk:17
ARG JAR_FILE=target/chat_server.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]