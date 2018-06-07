package Validation;

import entity.StartEndBahnhof;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CorrectEmail.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectEmailConstraint {
    String message() default "Email is invalid";
    Class<?>[] groups() default {};
    Class<? extends StartEndBahnhof>[] payload() default {};
}
