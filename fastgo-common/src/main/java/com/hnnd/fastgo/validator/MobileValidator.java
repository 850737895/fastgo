package com.hnnd.fastgo.validator;

import com.hnnd.fastgo.anno.IsMobile;
import com.hnnd.fastgo.util.ValidatorUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Administrator on 2018/12/20.
 */
public class MobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean isRequired=false;

    @Override
    public void initialize(IsMobile isMobile) {
        isRequired = isMobile.requried();
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        if(isRequired && !StringUtils.isEmpty(mobile)&& ValidatorUtils.checkMobileFormat(mobile)) {
            return true;
        }else {
            return false;
        }
    }
}
