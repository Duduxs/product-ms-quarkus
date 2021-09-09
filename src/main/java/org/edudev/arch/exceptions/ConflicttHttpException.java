package org.edudev.arch.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpResponseStatus.CONFLICT;

public final class ConflicttHttpException extends HttpExceptionHandler {

    public ConflicttHttpException(final String payload){ this(CONFLICT, payload); }

    ConflicttHttpException(final HttpResponseStatus status, final String payload) {
        super(status, payload);
    }
}