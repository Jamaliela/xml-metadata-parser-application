FROM openjdk:8-jre-alpine

COPY /target/sage-siparcs-xml-metadata-parser-0.0.2-SNAPSHOT.jar /usr/local/sage-siparcs-xml-metadata-parser-0.0.2-SNAPSHOT.jar

WORKDIR /usr/local

CMD ["/usr/bin/java", "-jar", "-Dspring.config.additional-location=/usr/local/properties/application.properties", "sage-siparcs-xml-metadata-parser-0.0.2-SNAPSHOT.jar"]