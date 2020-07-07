package edu.ucar.cisl.xmlparsing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MetadataTranslatorTest {

    private MetadataTranslator metadataTranslator;

    @Before
    public void setup() {

        Map<String, String> map = new HashMap<>();
        map.put("document", "publication");
        this.metadataTranslator = new MetadataTranslator(map);
    }

    @Test
    public void testMappingTranslation() {

        assertTranslated("document", "publication");
    }

    private void assertTranslated(String word, String translated) {

        String resourceTypeOutput = this.metadataTranslator.translate(word);
        assertEquals(translated, resourceTypeOutput);

    }

    @Test
    public void testMappingNotFoundDoNotTranslate() {

        assertNotTranslated("dataset");
        assertNotTranslated("collection");
        assertNotTranslated("model");
        assertNotTranslated("image");
        assertNotTranslated("software");
    }

    private void assertNotTranslated(String word) {

        String resourceTypeOutput = this.metadataTranslator.translate(word);
        assertEquals(word, resourceTypeOutput);
    }
}

