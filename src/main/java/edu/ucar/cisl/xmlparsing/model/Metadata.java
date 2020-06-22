package edu.ucar.cisl.xmlparsing.model;

import org.apache.solr.client.solrj.beans.Field;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "gmd:MD_Metadata")
public class Metadata {

    @Field("id")
    @XmlPath("gmd:fileIdentifier/gco:CharacterString/text()")
    private String fileIdentifier;

    @Field("title")
    @XmlPath("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:title/gco:CharacterString/text()")
    private String title;

    @Field("description")
    @XmlPath("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:abstract/gco:CharacterString/text()")
    private String description;

    @Field("doi")
    @XmlPath("gmd:dataSetURI/gco:CharacterString/text()")
    private String doi;

    @Field("keywords")
    @XmlPath("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:descriptiveKeywords/gmd:MD_Keywords/gmd:keyword/gco:CharacterString/text()")
    private String keywords;

    @Field("resource_type")
    @XmlPath("gmd:hierarchyLevel/gmd:MD_ScopeCode/text()")
    private String resourceType;

    @Field("bounding_box")
    @XmlPath("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:extent/gmd:EX_Extent/gmd:geographicElement/gmd:EX_GeographicBoundingBox/gmd:westBoundLongitude/gco:Decimal/text()")
    private Collection<Integer> boundingBox = new ArrayList<>();

    @XmlElement(name = "gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:citedResponsibleParty")
    private List<ResponsibleParty> citedResponsibleParties = new ArrayList<>();

    private String diskLocation;

    private String md5;

    public String getFileIdentifier() {
        return fileIdentifier;
    }

    public void setFileIdentifier(String fileIdentifier) {
        this.fileIdentifier = fileIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Collection<Integer> getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Collection<Integer> boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<ResponsibleParty> getCitedResponsibleParties() {
        return citedResponsibleParties;
    }

    public void setCitedResponsibleParties(List<ResponsibleParty> citedResponsibleParties) {
        this.citedResponsibleParties = citedResponsibleParties;
    }

    public String getDiskLocation() {
        return diskLocation;
    }

    public void setDiskLocation(String diskLocation) {
        this.diskLocation = diskLocation;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}

