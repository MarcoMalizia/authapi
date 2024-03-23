FROM eclipse-temurin:17-jdk-focal

EXPOSE 8081

# Refer to Maven build -> finalName
ARG JAR_FILE=target/authenticator-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /home/azureuser/myagent/_work/4/s

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} authenticator-0.0.1-SNAPSHOT.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","authenticator-0.0.1-SNAPSHOT.jar"]
