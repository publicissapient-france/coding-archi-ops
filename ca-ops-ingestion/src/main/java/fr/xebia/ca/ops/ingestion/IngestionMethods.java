package fr.xebia.ca.ops.ingestion;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class IngestionMethods {

    public static String getReferentielHost() { return System.getenv("CA_OPS_REFERENTIEL_SERVICE_HOST"); }

    public static String getTypeTrain(Anomalie anomalie) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Train>> trains = restTemplate.exchange(
                "http://" + getReferentielHost()+ "/trains",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Train>>() {});

        Train train = trains.getBody().stream().filter(x -> x.idTrain.equals(anomalie.idTrain))
                .findFirst().get();

        return train.typeTrain;
    }

    public static AnomalieCompletee completerAnomalie(Anomalie anomalie, String typeTrain) {
        AnomalieCompletee anomalieCompletee = new AnomalieCompletee();
        anomalieCompletee.category = anomalie.category;
        anomalieCompletee.sousCategory = anomalie.sousCategory;
        anomalieCompletee.creationTs = anomalie.creationTs;
        anomalieCompletee.idTrain = anomalie.idTrain;
        anomalieCompletee.value = anomalie.value;
        anomalieCompletee.typeTrain = typeTrain;

        return anomalieCompletee;
    }

    public static void sendToPersistence(AnomalieCompletee anomalieCompletee) {

    }
}