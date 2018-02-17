package com.example.custom.beanvalidation.constraints;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

@NotNull
@Documented
@Constraint(validatedBy = ValidLicenseKeyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLicenseKey {

    String message() default "{com.example.custom.beanvalidation.constraints.ValidLicenseKey.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}