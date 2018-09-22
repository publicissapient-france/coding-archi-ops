package fr.xebia.ca.ops.referentiel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class ReferentielMethods {

    public static String getProjectId() {
        return System.getenv("PROJECT_ID");
    }

    public static String getVersion() {
        return System.getenv("PROJECT_VERSION");
    }

    public static String getProjectBucket() {
        return System.getenv("PROJECT_BUCKET");
    }

    public static String getRefPath(String refName) {
        return System.getenv("REFS_FOLDER") + "/" + refName;
    }

    public static String getCredentialsPath() {
        return System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
    }

    public static String readResource(final String fileName, Charset charset) throws IOException {
        return Resources.toString(Resources.getResource(fileName), charset);
    }

    public static String getTrainsFile() {
        return System.getenv("TRAINS_REF_FILE_NAME");
    }

    public static List<Train> getTrains() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(readResource(getTrainsFile(), Charsets.UTF_8), new TypeReference<List<Train>>(){});
    }
}
