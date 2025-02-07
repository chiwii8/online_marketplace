package annotation;

import app.domain.CreditCard;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

class AnnotationYearMonthTest {
    private Validator validator;
    private static CreditCard creditCard;

    @BeforeAll
    static void setUpAll(){
        creditCard = new CreditCard();
        creditCard.setCvv("105");
        creditCard.setBrand("bbva");
        creditCard.setNumber("1234432112344321");
        creditCard.setOwner("Juan Pérez");
    }

    @BeforeEach
     void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    void testValidCardYearMonthCorrect(){
        creditCard.setExpirationYear(2026);
        creditCard.setExpirationMonth(10);

        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard);
        if(!violations.isEmpty()){
            for(ConstraintViolation<CreditCard> violation: violations){
                System.out.println("Propidad:" + violation.getPropertyPath());
                System.out.println("Mensaje" + violation.getMessage());
                System.out.println("valor inválido" + violation.getInvalidValue());
            }

        }
        Assertions.assertTrue(violations.isEmpty(),"The Expiration Date is valid");
    }

    @ParameterizedTest
    @CsvSource({
            "2025,1,The Expiration Month is not valid",
            "2024,1,The Expiration Year is not valid",
            "2024,1,The Expiration Date is not valid"
    })
    void testValidCardYearMonthIncorrect(int year,int month, String message){
        creditCard.setExpirationYear(year);
        creditCard.setExpirationMonth(month);
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard);
        Assertions.assertFalse(violations.isEmpty(),message);
    }

}
