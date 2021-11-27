FROM openjdk:11
COPY ./target/employee-management.jar employee-management.jar
ENTRYPOINT ["java","-jar","employee-management.jar"]