package edu.ucar.cisl.xmlparsing.model;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class MetadataSearchIndex {

    private HttpSolrClient solrClient;

    public MetadataSearchIndex(HttpSolrClient solrClient) {

        this.solrClient = solrClient;
    }

    public void indexMetadata(Metadata metadata) {

        try {

            tryIndexMetadata(metadata);

        } catch (Exception e){

            throw new RuntimeException(e);
        }
    }

    public void emptySolr() {

        try {

            tryEmptySolr();


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

    private void tryEmptySolr() throws IOException, SolrServerException {

        this.solrClient.deleteByQuery("*");
        this.solrClient.commit();
    }
    private void tryIndexMetadata(Metadata metadata) throws IOException, SolrServerException {

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", metadata.getFileIdentifier());
        if (metadata.getTitle() != null) {

            document.addField("title", metadata.getTitle());
        }
        if (metadata.getDescription() != null) {

            document.addField("description", metadata.getDescription());
        }
        if (metadata.getDoi() != null) {

            document.addField("doi", metadata.getDoi());
        }
        if (metadata.getKeywords() != null) {

            document.addField("keywords", metadata.getKeywords());

        }
        //document.addField("BoundingBox: ", metadata.getBoundingBox());
        for (String author : getAuthorNames(metadata.getCitedResponsibleParties())) {

            document.addField("authors", author);
        }
        for (String email : getAuthorEmails(metadata.getCitedResponsibleParties()) ) {

            document.addField("author_emails", email);
        }
        document.addField("authoritative_source_url", metadata.getDoi());
        document.addField("authoritative_source_location_on_disk", metadata.getDiskLocation());
        document.addField("authoritative_source_md5", metadata.getMd5());
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
