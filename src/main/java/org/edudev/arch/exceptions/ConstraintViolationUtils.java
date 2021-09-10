package org.edudev.arch.exceptions;

import javax.validation.Validation;
import javax.validation.Validator;

public final class ConstraintViolationUtils {

    public static void validate(final Object entity) {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final var violations = validator.validate(entity);

        if(!violations.isEmpty()) {
            final var violation = violations.stream().findFirst().get();
            throw new BadRequestHttpException(violation.getMessage());
        }
    }

}
