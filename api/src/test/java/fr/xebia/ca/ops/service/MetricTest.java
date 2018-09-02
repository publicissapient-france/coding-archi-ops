package fr.xebia.ca.ops.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;


public class MetricTest {

    private static final Metric METRIC = Metric.builder()
            .uuid("initialUUID")
            .creationDate(100)
            .idTrain("id_train")
            .category("category")
            .subCategory("subcat")
            .value("value")
            .build();

    @Test
    public void should_fail_to_set_null_UUID(){
        assertThatNullPointerException().isThrownBy(() ->
                METRIC.setUuid(null));
    }

    @Test
    public void should_set_UUID(){
        assertThat(METRIC.setUuid("123").getUuid())
                .isEqualTo("123");
    }

    @Test
    public void should_copy_all_fields_when_setting_UUID(){
        assertThat(METRIC.setUuid("123"))
                .isEqualToIgnoringGivenFields(METRIC, "uuid");
    }

}