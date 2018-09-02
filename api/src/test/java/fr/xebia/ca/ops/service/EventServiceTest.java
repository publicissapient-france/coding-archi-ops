package fr.xebia.ca.ops.service;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EventServiceTest {

    private static final Metric METRIC_1 = Metric.builder().creationDate(100).build();
    private static final Metric METRIC_2 = Metric.builder().creationDate(200).build();
    private static final Metric METRIC_3 = Metric.builder().creationDate(300).build();

    private final EventRepository repository = mock(EventRepository.class);
    private final EventService service = new EventService(repository);

    @Test
    public void should_sort_events(){
        given(this.repository.findAll())
                .willReturn(asList(METRIC_2, METRIC_1, METRIC_3));
        assertThat(this.service.listEvents())
                .containsExactly(METRIC_3, METRIC_2, METRIC_1);
    }

}