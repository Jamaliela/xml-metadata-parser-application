package edu.ucar.cisl.xmlparsing;

import edu.ucar.cisl.xmlparsing.model.MetadataRepository;
import edu.ucar.cisl.xmlparsing.model.MetadataSearchIndex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.util.Collection;

//@RestController
public class MetadataController {

    private MetadataRepository metadataRepository;
    private MetadataSearchIndex metadataSearchIndex;


    public MetadataController(MetadataRepository metadataRepository, MetadataSearchIndex metadataSearchIndex) {

        this.metadataRepository = metadataRepository;
        this.metadataSearchIndex = metadataSearchIndex;

    }

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String helloWorld(){
//
//        return "Hello World Spring Boot Application!";
//    }
//
//    @RequestMapping(path = "/metadata")
//    public String getMetadata(@RequestParam(name = "path") String path) {
//
//        return this.metadataRepository.get(path).getTitle();
//    }
//
//    @RequestMapping(path = "/addOrUpdate")
//    public String addOrUpdate(){
//
//        File directoryName = new File("classpath:application.properties");
//        Collection<File> xmlFiles = FileUtils.listFiles(directoryName, new WildcardFileFilter("*.xml"), null);
//        for (File file : xmlFiles) {
//
//            this.metadataSearchIndex.indexMetadata(this.metadataRepository.get(file.getAbsolutePath()));
//        }
//        return "New updated";
//    }
}
