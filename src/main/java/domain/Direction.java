package domain;

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
@EqualsAndHashCode(of = {"idDirection"})
public class Direction{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long idDirection;

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
    @Column(name="city", nullable = false)
    private String country;

    @Length(min = 5, max = 5)
    @Column(name = "postalcode" , nullable = false)
    private String postalCode;

    //TODO: Write the Patter for the phone number
    @Pattern(message = "The phone number is not valid", regexp = "")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}