package domain.actor;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import domain.CreditCard;
import domain.Direction;
import domain.Order;
import domain.Review;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person{

    ///Relations
    @OneToMany
    private List<Order> orders;

    @OneToMany
    private List<Direction> directions;

    @OneToMany
    private List<CreditCard> creditCards;

    @OneToMany
    private List<Review> reviews;



    @Override
    public void updateFrom(Person person){
        super.updateFrom(person);
    }
}