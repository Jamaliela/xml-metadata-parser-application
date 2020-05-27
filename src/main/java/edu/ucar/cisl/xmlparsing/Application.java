package edu.ucar.cisl.xmlparsing;

import edu.ucar.cisl.xmlparsing.model.Metadata;
import edu.ucar.cisl.xmlparsing.model.ResponsibleParty;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collection;


public class Application {

    private Unmarshaller xmlUnmarshaller;
    private File directory;
    private IOFileFilter ioFileFilter;

    public Application(Unmarshaller xmlUnmarshaller, File directory, IOFileFilter ioFileFilter) {

        this.xmlUnmarshaller = xmlUnmarshaller;
        this.directory = directory;
        this.ioFileFilter = ioFileFilter;

    }

    public static void main(String[] args) throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
        File directory = new File("/Users/ejamali/IdeaProjects/xmlparsing/src/main/temp/");
        IOFileFilter ioFileFilter = new WildcardFileFilter("*.xml");
        Application application = new Application(jaxbContext.createUnmarshaller(),directory, ioFileFilter);
        application.run();
    }

    private void run() throws Exception {

        Collection<File> xmlFiles = this.getFiles();
        for (File eachFile : xmlFiles) {

            System.out.println(eachFile.getName());
            Metadata metadata = (Metadata) this.xmlUnmarshaller.unmarshal(eachFile);
            this.printMetadata(metadata);
        }
    }

    private Collection<File> getFiles() {

        File directoryName = new File(String.valueOf(this.directory));
        return FileUtils.listFiles(directoryName, this.ioFileFilter, null);
    }

    private void printMetadata(Metadata metadata) {

        System.out.println("file Identifier: " + metadata.getFileIdentifier());
        System.out.println("Language: " + metadata.getLanguage());
        System.out.println("Character Set: " + metadata.getCharacterSet());
        System.out.println("----------------------------------------------");
        System.out.println("Contact:");
        printResponsibleParty(metadata.getContact());
        System.out.println("----------------------------------------------");
        System.out.println("Point of Contact:");
        printResponsibleParty(metadata.getPointOfContacts());
        System.out.println("----------------------------------------------");
        System.out.println("Distributing Contact:");
        if (metadata.getDistributorContact() != null) {

            printResponsibleParty(metadata.getDistributorContact());
        }
        System.out.println("----------------------------------------------");
        System.out.println("Cited Responsible Parties");
        for (ResponsibleParty author : metadata.getCitedResponsibleParties()) {

            printResponsibleParty(author);
        }
        System.out.println("----------------------------------------------");
    }

    private void printResponsibleParty (ResponsibleParty responsibleParty) {

        if (responsibleParty.getIndividualName() != null) {

            System.out.println("Individual Name: " + responsibleParty.getIndividualName());
        }
        if (responsibleParty.getOrganizationName() != null) {

            System.out.println("Organization Name: " + responsibleParty.getOrganizationName());
        }
        if (responsibleParty.getElectronicMailAddress() != null) {

            System.out.println("Email Address: " + responsibleParty.getElectronicMailAddress());
        }
        System.out.println("Role: " + responsibleParty.getRole());
    }
}