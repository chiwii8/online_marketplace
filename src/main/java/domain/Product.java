package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import domain.enumeration.ProductStatus;

import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"about","status"})
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ElementCollection
    @Column(name = "features",nullable = false)
    private Map<String,String> fetures;

    @NotNull
    @NotBlank
    @Column(name = "about", nullable = false)
    private String about;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    ///Relations
    @ManyToOne
    private List<Section> sections;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;
}