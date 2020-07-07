package edu.ucar.cisl.xmlparsing.model;

import java.util.Map;

public class MetadataTranslator {

    private Map<String, String> map;

    public MetadataTranslator(Map<String, String> map) {

        this.map = map;
    }

    public String translate(String resourceType) {

        if (this.map.containsKey(resourceType)) {

            return this.map.get(resourceType);
        }
        else {

            return resourceType;
        }
    }
}
