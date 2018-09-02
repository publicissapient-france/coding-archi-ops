package fr.xebia.ca.ops.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class InvalidMetricException
        extends RuntimeException {

    InvalidMetricException(final Metric content) {
        super(format("Metric content is invalid: %s", content));
    }

}
