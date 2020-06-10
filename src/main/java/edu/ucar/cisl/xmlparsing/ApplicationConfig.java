package edu.ucar.cisl.xmlparsing;

import edu.ucar.cisl.xmlparsing.model.Metadata;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Configuration
public class ApplicationConfig {

    @Bean
    public Unmarshaller getUnmarshaller() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);
        return jaxbContext.createUnmarshaller();
    }

    @Bean
    public SolrClient getSolrClient(@Value("${spring.data.solr.host}") String solrUrl) {

        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        solrClient.setParser(new XMLResponseParser());
        return solrClient;
    }

    @Bean
    public IOFileFilter getIoFileFilter() {

        return new WildcardFileFilter("*.xml");
    }

}
