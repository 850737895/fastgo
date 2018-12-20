package com.hnnd.fastgo.anno;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验器
 * Created by Administrator on 2018/12/20.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=Mobile.class)
public @interface IsMobile {

    String message() default"不是金额形式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
