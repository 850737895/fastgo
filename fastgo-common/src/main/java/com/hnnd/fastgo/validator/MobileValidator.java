package com.hnnd.fastgo.validator;

import com.hnnd.fastgo.anno.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Administrator on 2018/12/20.
 */
public class MobileValidator implements ConstraintValidator<IsMobile,String> {
    @Override
    public void initialize(IsMobile isMobile) {
        isMobile.
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
