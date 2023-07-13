FROM eclipse-temurin:17-jdk-alpine

RUN chmod +x gradlew

COPY ./target/product_backend.jar /application.jar

ENTRYPOINT ["java","-jar","/application.jar"]

EXPOSE 8080