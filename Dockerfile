FROM openjdk:11
EXPOSE 8085
ADD /target/zeraki-backend-0.0.1-SNAPSHOT.jar zeraki-backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","zeraki-backend-0.0.1-SNAPSHOT.jar"]