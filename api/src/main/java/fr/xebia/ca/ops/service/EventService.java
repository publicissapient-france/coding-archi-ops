package fr.xebia.ca.ops.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class EventService {

    private final EventRepository repository;

    public Optional<Metric> getEvent(final String uuid) {
        return this.repository.find(uuid);
    }

    public Metric addEvent(final Metric event) {
        return this.repository.add(event);
    }

    public List<Metric> listEvents() {
        return this.repository.findAll().stream()
                .sorted(comparing(Metric::getCreationDate).reversed())
                .collect(toList());
    }

}