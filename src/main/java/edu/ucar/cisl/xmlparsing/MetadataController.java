package edu.ucar.cisl.xmlparsing;

import edu.ucar.cisl.xmlparsing.model.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataController {

    private MetadataRepository metadataRepository;

    public MetadataController(MetadataRepository metadataRepository) {

        this.metadataRepository = metadataRepository;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String helloWorld(){

        return "Hello World Spring Boot Application!";
    }

    @RequestMapping(path = "/metadata")
    public String getMetadata(@RequestParam(name = "path") String path) {

        return this.metadataRepository.get(path).getTitle();
    }
}
