package domain;

import annotation.ValidCardYearMonth;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;



@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cvv"})
@EqualsAndHashCode(exclude = {"cvv"})
@ValidCardYearMonth(monthTag = "expirationMonth", yearTag = "expirationYear")
public class CreditCard{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    @NotBlank
    @Column(name = "owner", nullable = false)
    private String owner;

    @NotBlank
    @Column(name = "brand", nullable = false)
    private String brand;

    @CreditCardNumber
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "expirationMonth",nullable = false)
    private int expirationMonth;

    @Column(name = "expirationYear", nullable = false)
    private int expirationYear;

    @Length(min = 3,max = 3)
    @Column(name = "cvv", nullable = false)
    private String cvv;

    public void updateFrom(CreditCard creditCard){
        this.owner = creditCard.getOwner();
        this.brand = creditCard.getBrand();
        this.number = creditCard.getNumber();
        this.expirationMonth = creditCard.getExpirationMonth();
        this.expirationYear = creditCard.getExpirationYear();
        this.cvv = creditCard.getCvv();
    }
}