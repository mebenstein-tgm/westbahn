package Validation;

import entity.StartEndBahnhof;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrentDate.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentDateConstraint {
    String message() default "Date cannot be in the past";
    Class<?>[] groups() default {};
    Class<? extends StartEndBahnhof>[] payload() default {};
}
