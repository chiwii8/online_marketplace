package annotation;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CardYearMonthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCardYearMonth{
    String message() default "Year and month must not be in past";
    String yearTag() default "year";
    String monthTag() default "month";
}