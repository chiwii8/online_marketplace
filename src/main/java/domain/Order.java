package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import domain.enumeration.OrderStatus;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long idOrder;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identifier", nullable = false)
    private UUID identifier;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    ///Relations
    @NotNull
    @ElementCollection
    @ManyToMany
    List<Product> products;

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
    }

    public void updateFrom(Order order){
        this.identifier = order.getIdentifier();
        this.status = order.getStatus();
        this.products = order.getProducts();
    }

}