# sage-siparcs-xml-metadata-parser

The XML metadata parser is a [Spring Boot](https://spring.io/projects/spring-boot) application that parses external ISO Metadata XML files and then indexes that metadata into a Solr index. 

## Before Running

### Java

You need to have [OpenJDK](https://adoptopenjdk.net/index.html) 8 or later installed.

### Vagrant

For more information on how to set up your vagrant follow this GitHub [repository](https://github.com/NCAR/sage-solr-vagrant.git).

### Properties File

The application properties file (src/main/resources/application.properties) contains the spring.data.solr.host,and the xml.directory constants that should be changed before running. The constant spring.data.solr.host is the Solr URL path and xml.directory can is the path that stores your XML files which can be a GitHub repository.

Config File Examples:
```
spring.data.solr.host= http://localhost:8983/solr/<core name>
xml.directory= <path to a cloned GitHub repository>
```

## To Build and Run XML Metadata Parser
In order to run a self-executable JAR file you will need to:
- add the plugin that can create executable archives (jar files and war files) which contain all of an application’s dependencies and can then be run with ```java -jar```.
You can find the build plugin [here](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/maven-plugin/reference/html/#repackage)
- You need to have maven installed and run
  ```
  clean install spring-boot:repackage
  ```
- After running the repackage target file gets created with the self-executable jar file. Now, you can run the jar file separately from command line in the target folder with:
  ```
  java -Dspring.config.location=file://<path to properties file> -jar <jar file to be executed>
  ```


## Clearing Solr 

You can clear your solr responses by running this command in your terminal:
```
curl <spring.data.solr.host>/update?commit=true -H 'Content-Type: text/xml' --data-binary '<delete><query>*:*</query></delete>'
```


