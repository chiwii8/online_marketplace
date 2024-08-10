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

    @Column(name = "comercialName", nullable = false)
    private String comercialName;

    ///Relations

    @OneToMany
    private List<Product> productList;

}