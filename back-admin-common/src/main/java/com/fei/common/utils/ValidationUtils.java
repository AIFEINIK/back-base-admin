package com.fei.common.utils;

import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author ZhangPengFei
 * @description
 */
public class ValidationUtils {

    private static final Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(true).buildValidatorFactory().getValidator();

    public static void validate(Object obj) throws IllegalArgumentException {
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);
        if (!CollectionUtils.isEmpty(violations)) {
            ConstraintViolation<Object> violation = violations.iterator().next();
            throw new IllegalArgumentException(violation.getMessage());
        }
    }
}
