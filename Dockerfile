FROM eclipse-temurin:23
WORKDIR workspace
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} insurancepolicies.jar
ENTRYPOINT ["java", "-jar", "insurancepolicies.jar"]