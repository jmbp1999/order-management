FROM openjdk:17-oracle
COPY target/*.jar order-management.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","order-management.jar"]