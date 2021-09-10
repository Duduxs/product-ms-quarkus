package org.edudev.arch.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class HttpExceptionHandler extends WebApplicationException {

    private final HttpResponseStatus status;

    private final String payload;

    public HttpExceptionHandler(final HttpResponseStatus status, final String payload) {
        this.status = status;
        this.payload = payload;
        throwException();
    }

    private void throwException() {
        var response = Response
                .status(status.code())
                .entity(new ExceptionJsonMessage(status, payload))
                .type(APPLICATION_JSON)
                .build();

        throw new WebApplicationException(response);
    }

    private static final class ExceptionJsonMessage {

        private final Integer statusCode;

        private final String payload;

        public ExceptionJsonMessage(final HttpResponseStatus status, final String payload) {
            this.statusCode = status.code();
            this.payload = payload;
        }

        public Integer getStatus_code() { return statusCode; }

        public String getMessage() { return payload; }
    }
}



