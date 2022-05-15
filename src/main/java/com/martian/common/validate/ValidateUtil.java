package com.martian.common.validate;

import com.martian.common.exception.BusinessException;
import com.martian.common.exception.Exceptions;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 *
 */
public class ValidateUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidateResult validateEntity(T obj, Class clazz) {
        ValidateResult result = new ValidateResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, clazz);
        checkEntity(result, set);
        return result;
    }

    public static <T> ValidateResult validateProperty(T obj, String propertyName, Class clazz) {
        ValidateResult result = new ValidateResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, clazz);
        checkEntity(result, set);
        return result;
    }

    private static <T> void checkEntity(ValidateResult result, Set<ConstraintViolation<T>> set) {
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasError(true);
            for (ConstraintViolation<T> cv : set) {
                result.setErrorMsg(cv.getMessage());
                break;
            }
            throw new BusinessException(Exceptions.System.REQUEST_PARAM_ERROR, "【" + result.getErrorMsg() + "】");
        }
    }
}
