package edu.ucar.cisl.xmlparsing;

import edu.ucar.cisl.xmlparsing.model.Metadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@RestController
@ComponentScan
@EnableAutoConfiguration
public class SpringBootHelloWorld {

    @Bean
    public Unmarshaller getUnmarshaller() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
        return jaxbContext.createUnmarshaller();
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootHelloWorld.class, args);
    }
}
