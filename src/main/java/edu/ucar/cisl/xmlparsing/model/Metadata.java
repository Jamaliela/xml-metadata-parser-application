package edu.ucar.cisl.xmlparsing.model;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "gmd:MD_Metadata")
public class Metadata {

    @XmlPath("gmd:fileIdentifier/gco:CharacterString/text()")
    private String fileIdentifier;

    @XmlPath("gmd:language/gmd:LanguageCode/text()")
    private String language;

    @XmlPath("gmd:characterSet/gmd:MD_CharacterSetCode/text()")
    private String characterSet;

    @XmlElement(name = "gmd:contact")
    private ResponsibleParty contact;

    @XmlElement(name = "gmd:identificationInfo/gmd:MD_DataIdentification/gmd:pointOfContact")
    private ResponsibleParty pointOfContacts;

    @XmlElement(name = "gmd:distributionInfo/gmd:MD_Distribution/gmd:distributor/gmd:MD_Distributor/gmd:distributorContact")
    private ResponsibleParty distributorContact;

    @XmlElement(name = "gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:citedResponsibleParty")
    private List<ResponsibleParty> citedResponsibleParties = new ArrayList<>();

    public String getFileIdentifier() {
        return fileIdentifier;
    }

    public void setFileIdentifier(String fileIdentifier) {
        this.fileIdentifier = fileIdentifier;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    public ResponsibleParty getContact() {
        return contact;
    }

    public void setContact(ResponsibleParty contact) {
        this.contact = contact;
    }

    public ResponsibleParty getPointOfContacts() {
        return pointOfContacts;
    }

    public void setPointOfContacts(ResponsibleParty pointOfContacts) {
        this.pointOfContacts = pointOfContacts;
    }

    public ResponsibleParty getDistributorContact() {
        return distributorContact;
    }

    public void setDistributorContact(ResponsibleParty distributorContact) {
        this.distributorContact = distributorContact;
    }

    public List<ResponsibleParty> getCitedResponsibleParties() {
        return citedResponsibleParties;
    }

    public void setCitedResponsibleParties(List<ResponsibleParty> citedResponsibleParties) {
        this.citedResponsibleParties = citedResponsibleParties;
    }
}

