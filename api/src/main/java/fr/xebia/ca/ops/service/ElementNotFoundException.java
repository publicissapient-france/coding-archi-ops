package fr.xebia.ca.ops.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class ElementNotFoundException
        extends RuntimeException {

    public ElementNotFoundException(final String uuid) {
        super(format("No element found with UUID '%s'", uuid));
    }

}
