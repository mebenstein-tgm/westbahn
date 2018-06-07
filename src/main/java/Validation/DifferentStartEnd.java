package Validation;

import entity.StartEndBahnhof;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.*;


public class DifferentStartEnd implements ConstraintValidator<DifferentStartEndConstraint, StartEndBahnhof> {

    @Override
    public boolean isValid(StartEndBahnhof startEndBahnhof, ConstraintValidatorContext constraintValidatorContext) {
        return startEndBahnhof.getStart().getName() != startEndBahnhof.getEnde().getName();
    }

    @Override
    public void initialize(DifferentStartEndConstraint constraintAnnotation) {

    }
}
