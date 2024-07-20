FROM openjdk:11
COPY ./be/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Dspring.config.location=file:/home/ubuntu/be/conf","-jar","app.jar","--spring.profiles.active=dev"]
