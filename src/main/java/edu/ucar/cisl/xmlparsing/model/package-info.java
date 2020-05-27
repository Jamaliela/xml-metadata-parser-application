@XmlSchema(
        elementFormDefault = XmlNsForm.UNQUALIFIED,
        xmlns = {

                @XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
                @XmlNs(prefix = "gco", namespaceURI = "http://www.isotc211.org/2005/gco"),
                @XmlNs(prefix = "gmd", namespaceURI = "http://www.isotc211.org/2005/gmd"),
                @XmlNs(prefix = "gml", namespaceURI = "http://www.opengis.net/gml"),
                @XmlNs(prefix = "gmx", namespaceURI = "http://www.isotc211.org/2005/gmx"),
                @XmlNs(prefix = "xlink", namespaceURI = "http://www.w3.org/1999/xlink")
        })

package edu.ucar.cisl.xmlparsing.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;