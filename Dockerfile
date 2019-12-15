FROM openjdk:8u212-jre-alpine3.9

RUN mkdir /opt/app
COPY /target/interview-scheduler-authorization-1.0.jar /opt/app
EXPOSE 8096
CMD ["java", "-jar", "/opt/app/interview-scheduler-authorization-1.0.jar"]