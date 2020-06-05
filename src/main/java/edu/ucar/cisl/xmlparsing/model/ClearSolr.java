package edu.ucar.cisl.xmlparsing.model;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;


public class ClearSolr {

    public static void main(String[] args) throws Exception {

        String urlString = "http://localhost:8983/solr/metadata";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
        solrClient.setParser(new XMLResponseParser());
        solrClient.deleteByQuery("*");
        solrClient.commit();
    }
}
