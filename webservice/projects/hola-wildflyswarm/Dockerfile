FROM openjdk:8-jre
ADD target/hola-wildflyswarm-swarm.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
