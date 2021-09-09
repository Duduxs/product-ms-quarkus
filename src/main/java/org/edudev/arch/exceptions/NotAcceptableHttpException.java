package org.edudev.arch.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpResponseStatus.NOT_ACCEPTABLE;

public final class NotAcceptableHttpException extends HttpExceptionHandler {

    public NotAcceptableHttpException(final String payload){ this(NOT_ACCEPTABLE, payload); }

    NotAcceptableHttpException(final HttpResponseStatus status, final String payload) {
        super(status, payload);
    }
}