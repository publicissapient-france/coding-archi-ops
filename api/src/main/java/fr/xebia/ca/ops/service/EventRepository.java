package fr.xebia.ca.ops.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
class EventRepository {

    private final Map<String, Metric> elements = new ConcurrentHashMap<>();

    Metric add(@NonNull final Metric element) {
        if (getUuid(element) != null) {
            throw new InvalidMetricException(element);
        }
        final Metric elementWithId = element.setUuid(generateUuid());
        this.elements.putIfAbsent(getUuid(elementWithId), elementWithId);
        return elementWithId;
    }

    Optional<Metric> find(@NonNull final String uuid) {
        log.info("Get element with UUID '{}'", uuid);
        return Optional.ofNullable(this.elements.get(uuid));
    }

    Collection<Metric> findAll() {
        log.info("Get all element");
        return this.elements.values();
    }

    private static String getUuid(final Metric element) {
        return element.getUuid();
    }

    private static String generateUuid() {
        return UUID.randomUUID().toString();
    }

}
