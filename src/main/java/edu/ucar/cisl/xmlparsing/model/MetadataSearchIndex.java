package edu.ucar.cisl.xmlparsing.model;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class MetadataSearchIndex {

    private MetadataTranslator metadataTranslator;
    private SolrClient solrClient;

    public MetadataSearchIndex(SolrClient solrClient, MetadataTranslator metadataTranslator) {

        this.solrClient = solrClient;
        this.metadataTranslator = metadataTranslator;
    }

    public void indexMetadata(Metadata metadata) {

        try {

            tryIndexMetadata(metadata);

        } catch (Exception e){

            throw new RuntimeException(e);
        }
    }

    public Collection<String> AuthorEmails(Collection<ResponsibleParty> responsibleParties) {

        Collection<String> authorEmails;

        try {

            authorEmails = getAuthorEmails(responsibleParties);

        }catch (Exception e){

            throw new RuntimeException(e);
        }
        return authorEmails;
    }

    public Collection<String> AuthorNames(Collection<ResponsibleParty> responsibleParties) {

        Collection<String> authorNames;

        try {

            authorNames = getAuthorEmails(responsibleParties);

        }catch (Exception e){

            throw new RuntimeException(e);
        }
        return authorNames;
    }

    private void tryIndexMetadata(Metadata metadata) throws IOException, SolrServerException {

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", metadata.getFileIdentifier());
        if (metadata.getTitle() != null) {

            document.addField("title", metadata.getTitle().trim());
        }
        if (metadata.getDescription() != null) {

            document.addField("description", metadata.getDescription().trim());
        }
        if (metadata.getDoi() != null) {

            document.addField("doi", metadata.getDoi().trim());
        }
        if (metadata.getKeywords() != null) {

            document.addField("keywords", metadata.getKeywords().trim());

        }
        if (metadata.getResourceType() != null) {

            String resourceType = this.metadataTranslator.translator(metadata.getResourceType().trim());
            document.addField("resource_type", resourceType);
        }
        //document.addField("BoundingBox: ", metadata.getBoundingBox());
        for (String author : getAuthorNames(metadata.getCitedResponsibleParties())) {

            document.addField("authors", author);
        }
        for (String email : getAuthorEmails(metadata.getCitedResponsibleParties()) ) {

            document.addField("author_emails", email);
        }
        document.addField("authoritative_source_url", metadata.getDoi().trim());
        document.addField("authoritative_source_location_on_disk", metadata.getDiskLocation().trim());
        document.addField("authoritative_source_md5", metadata.getMd5().trim());
        this.solrClient.add(document);
        this.solrClient.commit();
    }

    private Collection<String> getAuthorNames(Collection<ResponsibleParty> responsibleParties) {

        Collection<String> authorNames = responsibleParties.stream()
                .filter(rp -> rp.getRole().equalsIgnoreCase("author"))
                .filter(rp -> rp.getIndividualName() != null)
                .map(rp -> rp.getIndividualName())
                .collect(Collectors.toList());
        return authorNames;
    }

    private Collection<String> getAuthorEmails(Collection<ResponsibleParty> responsibleParties){

        Collection<String> authorEmails = responsibleParties.stream()
                .filter(rp -> rp.getRole().equalsIgnoreCase("author"))
                .filter(rp -> rp.getElectronicMailAddress() != null)
                .map(rp -> rp.getElectronicMailAddress())
                .collect(Collectors.toList());
        return authorEmails;
    }
}
