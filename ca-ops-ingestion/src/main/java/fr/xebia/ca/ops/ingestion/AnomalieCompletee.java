package fr.xebia.ca.ops.ingestion;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class AnomalieCompletee {
    String category;
    String sousCategory;
    String idTrain;
    String typeTrain;
    Long creationTs;
    Long value;
}