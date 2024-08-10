package domain.actor;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id","email"})
public abstract class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surnames", nullable = false)
    private String surnames;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

}
