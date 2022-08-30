package com.redelles.tasks.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {

    public NotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
