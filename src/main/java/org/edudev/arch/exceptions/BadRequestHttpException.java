package org.edudev.arch.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

public final class BadRequestHttpException extends HttpExceptionHandler {

    public BadRequestHttpException(final String payload){ this(BAD_REQUEST, payload); }

    BadRequestHttpException(final HttpResponseStatus status, final String payload) {
        super(status, payload);
    }
}