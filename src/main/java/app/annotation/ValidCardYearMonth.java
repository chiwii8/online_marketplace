package app.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CardYearMonthValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCardYearMonth{
    String message() default "Year and month must not be in past";
    String yearTag() default "year";
    String monthTag() default "month";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}