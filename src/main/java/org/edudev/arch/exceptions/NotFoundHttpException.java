package org.edudev.arch.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;

public final class NotFoundHttpException extends HttpExceptionHandler {

    public NotFoundHttpException(final String payload){ this(NOT_FOUND, payload); }

    NotFoundHttpException(final HttpResponseStatus status, final String payload) {
        super(status, payload);
    }
}