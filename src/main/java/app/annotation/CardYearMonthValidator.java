package annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.YearMonth;
import java.util.logging.Logger;

public class CardYearMonthValidator implements ConstraintValidator<ValidCardYearMonth,Object>{

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void initialize(ValidCardYearMonth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object yearMonthClass, ConstraintValidatorContext context) {
        if(yearMonthClass == null){
            return false;
        }

        try{

            var classType = yearMonthClass.getClass();
            ValidCardYearMonth annotation = classType.getAnnotation(ValidCardYearMonth.class);
            Field yearField = classType.getDeclaredField(annotation.yearTag());
            Field monthField = classType.getDeclaredField(annotation.monthTag());

            Integer year = (Integer) yearField.get(yearMonthClass);
            Integer month = (Integer) monthField.get(yearMonthClass);

            if(year == null || month == null){
                return false;
            }

            YearMonth currentYearMonth = YearMonth.now();
            YearMonth inputYearMonth = YearMonth.of(year, month);

            return !inputYearMonth.isBefore(currentYearMonth);

        }catch(Exception ex){
            logger.info("Problems with the validation of the YearMonth CreditCard");
            return false;
        }
    }

}