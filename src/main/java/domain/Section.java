package domain;

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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Column(name = "name" , nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    ///Relations
    @OneToMany
    private List<Section> subsections;

    @ManyToOne
    private Section previousSection;

}