package fr.xebia.ca.ops.ingestion;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class Anomalie {
    String category;
    String sousCategory;
    String idTrain;
    Long creationTs;
    Long value;
}