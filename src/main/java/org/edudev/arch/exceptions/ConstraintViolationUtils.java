package org.edudev.arch.exceptions;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public class ConstraintViolationUtils {

    public static void validate(final Object entity) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var violations = validator.validate(entity);

        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
