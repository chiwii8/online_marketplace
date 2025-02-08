package app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Section{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name" , nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "sections")
    private List<Product> products;

    ///Relations
    @OneToMany(mappedBy = "previousSection",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Section> subsections;

    @ManyToOne
    @JoinColumn(name="previous_section_id")
    private Section previousSection;

    public void updateFrom(Section section){
        this.name = section.getName();
        this.description = section.getDescription();
    }
}