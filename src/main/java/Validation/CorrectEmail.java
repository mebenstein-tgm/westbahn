package Validation;

import entity.StartEndBahnhof;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class CorrectEmail implements ConstraintValidator<CorrectEmailConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[a-z0-9.]+@[a-z0-9]+\\.[a-z0-9]+");
    }

    @Override
    public void initialize(CorrectEmailConstraint constraintAnnotation) {

    }
}
