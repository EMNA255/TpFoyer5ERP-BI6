FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tp-foyer.war tp-foyer.war
ENTRYPOINT ["java","-jar","/tp-foyer.war"]
