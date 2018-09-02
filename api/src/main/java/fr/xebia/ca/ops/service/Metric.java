package fr.xebia.ca.ops.service;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public final class Metric {

    String uuid;
    long creationDate;
    String idTrain;
    String category;
    String subCategory;
    String value;

    Metric setUuid(@NonNull final String uuid) {
        return Metric.builder()
                .uuid(uuid)
                .creationDate(this.getCreationDate())
                .idTrain(this.getIdTrain())
                .category(this.getCategory())
                .subCategory(this.getSubCategory())
                .value(this.getValue())
                .build();
    }

}