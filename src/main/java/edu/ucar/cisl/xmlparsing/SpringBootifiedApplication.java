package edu.ucar.cisl.xmlparsing;

import edu.ucar.cisl.xmlparsing.model.Metadata;
import edu.ucar.cisl.xmlparsing.model.MetadataRepository;
import edu.ucar.cisl.xmlparsing.model.MetadataSearchIndex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Collection;

@SpringBootApplication
public class SpringBootifiedApplication implements CommandLineRunner {


    private File directory;
    private IOFileFilter ioFileFilter;
    private MetadataSearchIndex metadataSearchIndex;
    private MetadataRepository metadataRepository;

    @Autowired
    public SpringBootifiedApplication(@Value("${xml.directory}") File directory,
                                      IOFileFilter ioFileFilter, MetadataSearchIndex metadataSearchIndex,
                                      MetadataRepository metadataRepository) {

        this.directory = directory;
        this.ioFileFilter = ioFileFilter;
        this.metadataSearchIndex = metadataSearchIndex;
        this.metadataRepository = metadataRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootifiedApplication.class, args);
    }

    private Collection<File> getXmlMetadataFiles() {

        return FileUtils.listFiles(this.directory, new String[]{"xml"}, true);
    }

    @Override
    public void run(String... args) {

        Collection<File> xmlFiles = this.getXmlMetadataFiles();
        for (File file : xmlFiles) {
            try {

                System.out.println("The indexed XML Document: " + file.getName());
                Metadata metadata = this.metadataRepository.get(file.getAbsolutePath());
                this.metadataSearchIndex.indexMetadata(metadata);
            } catch (Exception e) {

                System.out.println("Exception occurred in one the XML Files.");
                e.printStackTrace();
            }
        }
    }
}

