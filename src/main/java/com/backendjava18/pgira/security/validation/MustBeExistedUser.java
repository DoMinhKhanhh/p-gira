package com.backendjava18.pgira.security.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MustBeExistedUserValidator.class)
@Target(ElementType.FIELD)
public @interface MustBeExistedUser {
    String message() default "{user.not.existed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
