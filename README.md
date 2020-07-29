# sage-siparcs-xml-metadata-parser

The XML metadata parser is a [Spring Boot](https://spring.io/projects/spring-boot) application that parses external ISO Metadata XML files and then indexes that metadata into a Solr index. 

## Before Running

### Solr

For more information on how to set up your Solr instance follow this Github [repository](https://github.com/NCAR/sage-solr-vagrant.git).

### Java

You need to have [OpenJDK](https://adoptopenjdk.net/index.html) 8 or later installed.

### Vagrant

For more information on how to set up your vagrant follow this GitHub [repository](https://github.com/NCAR/sage-solr-vagrant.git).

### Docker Swarm
```
docker swarm init
docker stack deploy -c <docker-compose.yaml> <custom-stack-name>
```
### Docker Config 
```
docker config create <application.properties.20200716> <application.properties>
```
### Docker Secret 
```
docker secret create <application.properties.20200717> <application.properties>
```
### Dockerfile

The Dockerfile contains the version of Java,and it should copy the self-executable JAR file to the work directory.

Dockerfile Example:
```
FROM openjdk:8-jre-alpine

COPY <path-to-JAR-file> <path-to-be-copied-in-docker>

WORKDIR /usr/local

CMD ["/usr/bin/java", "-jar", "-Dspring.config.additional-location=/usr/local/properties/application.properties", "<JAR-file>"]
```
### Docker Compose File

For more information on how to create docker-compose.yaml files for your services follow this Github [repository](https://github.com/NCAR/sage-siparcs-docker-compose).

### Properties File

The application properties file (src/main/resources/application.properties) contains the spring.data.solr.host,and the xml.directory constants that should be changed before running. The constant spring.data.solr.host is the Solr URL path and xml.directory can is the path that stores your XML files which can be a GitHub repository.

Properties File Examples:
```
spring.data.solr.host= http://localhost:8983/solr/<core name>
xml.directory= <path to a cloned GitHub repository>
```

## To Build and Run XML Metadata Parser
In order to run a self-executable JAR file you will need to:
- add the maven plugin that can create executable archives. You can find the build plugin [here](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/maven-plugin/reference/html/#repackage)
- You need to have maven installed and run
  ```
  clean install spring-boot:repackage
  ```
- Now, you can run the jar file separately from command line in the target folder with:
  ```
  java -Dspring.config.location=file://<path to properties file> -jar <jar file to be executed>
  ```


## Clearing Solr 

You can clear your solr responses by running this command in your terminal:
```
curl <spring.data.solr.host>/update?commit=true -H 'Content-Type: text/xml' --data-binary '<delete><query>*:*</query></delete>'
```


