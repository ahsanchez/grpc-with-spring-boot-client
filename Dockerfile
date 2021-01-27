FROM openjdk:8-jre-alpine

COPY target/grpc-with-spring-boot.jar grpc-with-spring-boot.jar

ENTRYPOINT ["java", "-jar", "/grpc-with-spring-boot.jar"]
