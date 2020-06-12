# sage-siparcs-xml-metadata-parser

## Purpose

XML metadata parser is an spring boot application that parses external XML files and indexes the metadata needed to a Solr instance. 

## Before Running

### Java

You need to have [OpenJDK](https://adoptopenjdk.net/index.html) 8 or later installed.

### Vagrant

For more information on how to set up your vagrant follow this GitHub [repository](https://github.com/NCAR/sage-solr-vagrant.git).

### Properties File

The application properties file (src/main/resources/application.properties) contains the spring.data.solr.host,and the xml.directory constants that should be changed before running. The constant spring.data.solr.host is the Solr URL path and xml.directory can is the path that stores your XML files which can be a GitHub repository.

Config File Examples:

spring.data.solr.host= http://localhost:8983/solr/<core name>
xml.directory= <path to a cloned GitHub repository>

## Clearing Solr 

You can clear your solr responses by running this command in your terminal:
```
curl <spring.data.solr.host>/update?commit=true -H 'Content-Type: text/xml' --data-binary '<delete><query>*:*</query></delete>'
```


