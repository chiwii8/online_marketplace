package app.domain.actor;

import app.domain.actor.account.UserAccount;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id","email"})
public abstract class Person{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "Name", nullable = false)
    private String name;


    @Column(name = "Surnames", nullable = false)
    private String surnames;


    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "phoneNumber")
    private String phoneNumber;

    ///Relaciones
    @OneToOne(cascade = CascadeType.ALL,optional = false)
    private UserAccount userAccount;



    public void updateFrom(Person person){
        this.name = person.getName();
        this.surnames = person.getSurnames();
        this.email = person.getEmail();
        this.phoneNumber = person.getPhoneNumber();
    }

}
