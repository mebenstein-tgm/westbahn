package Validation;

import entity.StartEndBahnhof;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BahnhofName.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BahnhofNameConstraint {
    String message() default "Name must be longer than 2 chars and shorter than 150 chars without any special chars but dash";
    Class<?>[] groups() default {};
    Class<? extends StartEndBahnhof>[] payload() default {};
}
