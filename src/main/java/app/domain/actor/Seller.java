package domain.actor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import domain.Product;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Seller extends Person{

    @Column(name = "commercialName", nullable = false)
    private String commercialName;

    ///Relations

    @OneToMany(mappedBy = "seller")
    private List<Product> productList;

    @Override
    public void updateFrom(Person person){
        super.updateFrom(person);
        if(person instanceof Seller seller){
            this.commercialName = seller.getCommercialName();
        }
    }
}