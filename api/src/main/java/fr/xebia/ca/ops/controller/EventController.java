package fr.xebia.ca.ops.controller;

import fr.xebia.ca.ops.service.ElementNotFoundException;
import fr.xebia.ca.ops.service.EventService;
import fr.xebia.ca.ops.service.Metric;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/events")
@Api(tags = "Events")
public class EventController {

    private final EventService service;

    @PostMapping
    @ApiOperation(value = "Add event to repository")
    public ResponseEntity<Void> addEvent(@ApiParam(value = "Event information", required = true)
                           @RequestBody final Metric event) {
        log.info("POST request: event={}", event);
        final Metric createdEvent = this.service.addEvent(event);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(createdEvent.getUuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{uuid}")
    @ApiOperation(value = "Get information about the event")
    public Metric getEvent(@ApiParam(value = "Reference of the event in the data store", required = true)
                              @PathVariable final String uuid) {
        log.info("GET request: ID={}", uuid);
        return this.service.getEvent(uuid)
                .orElseThrow(() -> new ElementNotFoundException(uuid));
    }

    @GetMapping
    @ApiOperation(value = "Get list of all events")
    public List<Metric> listEvents() {
        log.info("GET request");
        return this.service.listEvents();
    }

}
