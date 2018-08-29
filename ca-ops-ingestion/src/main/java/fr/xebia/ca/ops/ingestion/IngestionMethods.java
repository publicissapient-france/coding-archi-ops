package fr.xebia.ca.ops.ingestion;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class IngestionMethods {

    public static String getReferentielHost() { return "ca-ops-referentiel"; }

    public static String getTypeTrain(Anomalie anomalie) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Train>> trains = restTemplate.exchange(
                "http://" + getReferentielHost()+ "/trains",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Train>>() {});

        Train train = trains.getBody().stream().filter(x -> x.getIdTrain().equals(anomalie.getIdTrain()))
                .findFirst().get();

        return train.getTypeTrain();
    }

    public static AnomalieCompletee completerAnomalie(Anomalie anomalie, String typeTrain) {

        return AnomalieCompletee.builder()
                .category(anomalie.getCategory())
                .creationTs(anomalie.getCreationTs())
                .idTrain(anomalie.getIdTrain())
                .sousCategory(anomalie.getSousCategory())
                .typeTrain(typeTrain)
                .value(anomalie.getValue())
                .build();
    }

    public static void sendToPersistence(AnomalieCompletee anomalieCompletee) {

    }
}