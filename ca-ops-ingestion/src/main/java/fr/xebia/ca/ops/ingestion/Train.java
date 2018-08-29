package fr.xebia.ca.ops.ingestion;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class Train {

    String idTrain;
    String typeTrain;
}