package com.nwuer.core.common.annotation;


import com.nwuer.core.common.validator.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author harbo
 * @date 6.13 23:40
 * @see java.lang.annotation.Annotation
 * 注解，验证两次输入密码是否一致
 */

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "两次密码输入不一致";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}