package com.spring.eStore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value.isBlank())
            return  false;
        return true;
    }
}
