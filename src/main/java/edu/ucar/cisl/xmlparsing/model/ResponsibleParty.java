package edu.ucar.cisl.xmlparsing.model;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class ResponsibleParty {

    @XmlPath("gmd:CI_ResponsibleParty/gmd:individualName/gco:CharacterString/text()")
    private String individualName;

    @XmlPath("gmd:CI_ResponsibleParty/gmd:organisationName/gco:CharacterString/text()")
    private String organizationName;

    @XmlPath("gmd:CI_ResponsibleParty/gmd:contactInfo/gmd:CI_Contact/gmd:address/gmd:CI_Address/gmd:electronicMailAddress/gco:CharacterString/text()")
    private String electronicMailAddress;

    @XmlPath("gmd:CI_ResponsibleParty/gmd:role/gmd:CI_RoleCode/text()")
    private String role;

    public String getIndividualName() {
        return individualName;
    }

    public void setIndividualName(String individualName) {
        this.individualName = individualName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getElectronicMailAddress() {
        return electronicMailAddress;
    }

    public void setElectronicMailAddress(String electronicMailAddress) {
        this.electronicMailAddress = electronicMailAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
