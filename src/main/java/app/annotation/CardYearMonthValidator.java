package app.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.YearMonth;
import java.util.logging.Logger;

public class CardYearMonthValidator implements ConstraintValidator<ValidCardYearMonth,Object>{

    private final Logger logger = Logger.getLogger(getClass().getName());

    private String yearTag;
    private String monthTag;

    @Override
    public void initialize(ValidCardYearMonth constraintAnnotation) {
        this.monthTag = constraintAnnotation.monthTag();
        this.yearTag = constraintAnnotation.yearTag();
    }

    @Override
    public boolean isValid(Object yearMonthClass, ConstraintValidatorContext context) {
        if(yearMonthClass == null){
            return false;
        }

        try{

            var classType = yearMonthClass.getClass();

            Method yearGetter = getterName(classType,yearTag);
            Method monthGetter = getterName(classType,monthTag);

            Integer year = (Integer) yearGetter.invoke(yearMonthClass);
            Integer month = (Integer) monthGetter.invoke(yearMonthClass);

            YearMonth currentYearMonth = YearMonth.now();
            YearMonth inputYearMonth = YearMonth.of(year, month);

            return inputYearMonth.isAfter(currentYearMonth) ;

        }catch (NoSuchMethodException methodException){
            logger.info("The Year or Month has not been detected getter method");
            logger.info("check if the attributes follows the Cammel case nomenclature");
        } catch(Exception ex){
            logger.info("Problems with the validation of the Actual year or month ");
        }
        return false;
    }

    public Method getterName(Class<?> className, String attributeName ) throws NoSuchMethodException{
        String getAttribute = "get" + attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
        return className.getDeclaredMethod(getAttribute);
    }

}