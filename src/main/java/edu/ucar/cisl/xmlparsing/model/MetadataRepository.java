package edu.ucar.cisl.xmlparsing.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class MetadataRepository {

    private Unmarshaller xmlUnmarshaller;

    public MetadataRepository(Unmarshaller xmlUnmarshaller) {

        this.xmlUnmarshaller = xmlUnmarshaller;
    }

    public Metadata get(String path) {

        Metadata metadata;

        try {

            metadata = tryGet(path);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return metadata;
    }

    private Metadata tryGet(String path) throws JAXBException, IOException {

        Metadata metadata = (Metadata) xmlUnmarshaller.unmarshal(new File(path));
        metadata.setDiskLocation(path);
        metadata.setMd5(getMd5(path));
        return metadata;
    }

    private String getMd5(String filePath) throws IOException {

        FileInputStream inputStream = new FileInputStream(filePath);
        String md5Value = DigestUtils.md5Hex(IOUtils.toByteArray(inputStream));
        IOUtils.closeQuietly(inputStream);
        return md5Value;
    }
}
