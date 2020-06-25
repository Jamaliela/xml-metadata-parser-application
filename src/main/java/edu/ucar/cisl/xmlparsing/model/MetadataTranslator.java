package edu.ucar.cisl.xmlparsing.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetadataTranslator {

    public String translator(String resourceType) {

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("document", "publication");
        if (hashMap.containsKey(resourceType)) {

            return hashMap.get(resourceType);
        }
        else {

            return resourceType;
        }
    }
}
