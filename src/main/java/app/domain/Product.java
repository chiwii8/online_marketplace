package app.domain;

import app.domain.actor.Seller;
import app.domain.enumeration.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"about","status"})
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "about", nullable = false)
    private String about;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    ///Relations
    @OneToMany
    private List<Section> sections;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToOne
    private Seller seller;

    /**
     * This method simplify and encapsulated the update of the attributes of the object
     * @param product contain the features updated for the user
     */
    public void updateFrom(Product product){
        this.name = product.getName();
        this.about = product.getAbout();
        this.reviews = product.getReviews();
        this.status = product.getStatus();
        this.sections = product.getSections();
        this.seller = product.getSeller();
    }
}