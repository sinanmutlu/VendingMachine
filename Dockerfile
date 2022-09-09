##FROM maven:3.8.2-jdk-11
##WORKDIR /vendingmachine
##COPY . .
##RUN mvn clean install
##CMD mvn spring-boot:run
FROM maven:3.8.2-jdk-11
WORKDIR /vendingmachine
COPY target/vendingmachine-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar","vendingmachine-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083