package com.redelles.tasks.exceptions;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler to manage all customized exceptions
     *
     * @param ex
     *     {@link ApplicationException} Generic application exception
     * @param request
     *     {@link WebRequest}
     * @return The message and http status defined on the custom {@link ApplicationException}
     */
    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<Object> applicationException(final ApplicationException ex, final WebRequest request) {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> notFound(final Exception ex, final WebRequest request) {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers,
        final HttpStatus status, final WebRequest request) {

        final Map<String, Object> body = new ConcurrentHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        final List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }
}

