package fr.xebia.ca.ops.referentiel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class ReferentielMethods {

    public static String getProjectId() {
        return System.getenv("PROJECT_ID");
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

    public static String getTrainsFile() {
        return System.getenv("TRAINS_REF_FILE_NAME");
    }

    public static List<Train> getTrains() throws IOException {
        GoogleCredentials credentials;
        File credentialsPath = new File(getCredentialsPath());
        try (FileInputStream serviceAccountStream = new FileInputStream(credentialsPath)) {
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
        }

        Storage storage = StorageOptions.newBuilder()
                .setProjectId(getProjectId())
                .setCredentials(credentials)
                .build()
                .getService();

        Blob blob = storage.get(getProjectBucket(), getRefPath(getTrainsFile()));

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new String(blob.getContent()), new TypeReference<List<Train>>(){});
    }
}
