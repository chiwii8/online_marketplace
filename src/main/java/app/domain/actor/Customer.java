package app.domain.actor;

import app.domain.Direction;
import app.domain.Order;
import app.domain.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import app.domain.CreditCard;

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