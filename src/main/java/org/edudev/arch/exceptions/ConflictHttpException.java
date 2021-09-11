package org.edudev.arch.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpResponseStatus.CONFLICT;

public final class ConflictHttpException extends HttpExceptionHandler {

    public ConflictHttpException(final String payload){ this(CONFLICT, payload); }

    ConflictHttpException(final HttpResponseStatus status, final String payload) {
        super(status, payload);
    }
}