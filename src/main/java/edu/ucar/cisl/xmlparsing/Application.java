//package edu.ucar.cisl.xmlparsing;
//
//import edu.ucar.cisl.xmlparsing.model.Metadata;
//import edu.ucar.cisl.xmlparsing.model.MetadataRepository;
//import edu.ucar.cisl.xmlparsing.model.MetadataSearchIndex;
//import edu.ucar.cisl.xmlparsing.model.ResponsibleParty;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.filefilter.IOFileFilter;
//import org.apache.commons.io.filefilter.WildcardFileFilter;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.impl.XMLResponseParser;
//
//import javax.xml.bind.JAXBContext;
//import java.io.File;
//import java.util.Collection;
//
//
//public class Application {
//
//    private MetadataSearchIndex metadataSearchIndex;
//    private MetadataRepository metadataRepository;
//    private File directory;
//    private IOFileFilter ioFileFilter;
//
//    public Application(MetadataSearchIndex metadataSearchIndex, MetadataRepository metadataRepository, File directory, IOFileFilter ioFileFilter) {
//
//        this.metadataSearchIndex = metadataSearchIndex;
//        this.metadataRepository = metadataRepository;
//        this.directory = directory;
//        this.ioFileFilter = ioFileFilter;
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        String urlString = "http://localhost:8983/solr/metadata";
//        HttpSolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
//        solrClient.setParser(new XMLResponseParser());
//        MetadataSearchIndex metadataSearchIndex = new MetadataSearchIndex(solrClient);
//        JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
//        MetadataRepository metadataRepository = new MetadataRepository(jaxbContext.createUnmarshaller());
//        File directory = new File("/Users/ejamali/IdeaProjects/sage-siparcs-xml-metadata-parser/src/main/resources/");
//        IOFileFilter ioFileFilter = new WildcardFileFilter("*.xml");
//        Application application = new Application(metadataSearchIndex, metadataRepository, directory, ioFileFilter);
//        application.run();
//    }
//
//    private void run() {
//
//        Collection<File> xmlFiles = this.getFiles();
//        for (File file : xmlFiles) {
//            try {
//
//                System.out.println("The indexed XML Document: " + file.getName());
//                Metadata metadata = this.metadataRepository.get(file.getAbsolutePath());
//                this.metadataSearchIndex.indexMetadata(metadata);
//                //this.printMetadata(metadata);
//            } catch (Exception e) {
//
//                System.out.println("Exception occurred in one the XML Files.");
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private Collection<File> getFiles() {
//
//        File directoryName = new File(String.valueOf(this.directory));
//        return FileUtils.listFiles(directoryName, this.ioFileFilter, null);
//    }
//
//    private void printMetadata(Metadata metadata) {
//
//        System.out.println("file Identifier: " + metadata.getFileIdentifier());
//        if (metadata.getTitle() != null) {
//
//            System.out.println("Title: " + metadata.getTitle());
//        }
//        if (metadata.getDescription() != null) {
//
//            System.out.println("Description: " + metadata.getDescription());
//        }
//        if (metadata.getDoi() != null) {
//
//            System.out.println("DOI: " + metadata.getDoi());
//        }
//        if (metadata.getKeywords() != null) {
//
//            System.out.println("Keywords: " + metadata.getKeywords());
//        }
//        //System.out.println("BoundingBox: " + metadata.getBoundingBox());
//        System.out.println("Author Names: ");
//        printAuthorNames(metadata.getCitedResponsibleParties());
//        System.out.println("Author Emails: ");
//        printAuthorEmail(metadata.getCitedResponsibleParties());
//        System.out.println("authoritative_source_url: " + metadata.getDoi());
//        System.out.println("authoritative_source_location_on_disk: " + metadata.getDiskLocation());
//        System.out.println("authoritative_source_md5: " + metadata.getMd5());
//        System.out.println("--------------------------------------------");
//    }
//
//    private void printAuthorNames(Collection<ResponsibleParty> responsibleParties) {
//
//        this.metadataSearchIndex.AuthorNames(responsibleParties).stream()
//                .forEach(System.out::println);
//    }
//
//    private void printAuthorEmail (Collection<ResponsibleParty> responsibleParties) {
//
//        this.metadataSearchIndex.AuthorEmails(responsibleParties).stream()
//                .forEach(System.out :: println);
//    }
//}