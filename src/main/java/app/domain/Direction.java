package app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Direction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "direction" , nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @NotBlank
    @Column(name="country", nullable = false)
    private String country;

    @Length(min = 5, max = 5)
    @Column(name = "postcode" , nullable = false)
    private String postalCode;

    @Pattern(message = "The phone number is not valid", regexp = "^(\\+\\d+\\s+)?(\\(\\d+\\)\\s+)?\\d{4,}")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public void updateFrom(Direction direction){
        this.name = direction.getName();
        this.city = direction.getCity();
        this.country = direction.getCountry();
        this.postalCode = direction.getPostalCode();
        this.phoneNumber = direction.getPhoneNumber();
    }
}