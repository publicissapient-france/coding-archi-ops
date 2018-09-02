package fr.xebia.ca.ops.controller;

import fr.xebia.ca.ops.service.ElementNotFoundException;
import fr.xebia.ca.ops.service.EventService;
import fr.xebia.ca.ops.service.Metric;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EventControllerTest {

    private final EventService service = mock(EventService.class);
    private final EventController controller = new EventController(service);

    @Test
    public void should_find_existing_event(){
        final Metric event = Metric.builder().build();
        given(this.service.getEvent("123"))
                .willReturn(Optional.of(event));
        assertThat(this.controller.getEvent("123"))
                .isEqualTo(event);
    }

    @Test
    public void should_not_find_unknown_event(){
        given(this.service.getEvent("123"))
                .willReturn(Optional.empty());
        assertThatExceptionOfType(ElementNotFoundException.class).isThrownBy(() ->
                this.controller.getEvent("123"))
                .withMessage("No element found with UUID '123'");
    }

}