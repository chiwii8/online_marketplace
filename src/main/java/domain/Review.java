package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"comment"})
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long idReview;

    @Range(min = 0, max = 5)
    @Column(name = "puntuation", nullable = false)
    private double puntuation;

    @NotNull
    @Column(name = "comment" , nullable = false)
    private String comment;

    ///Relations
    @ManyToOne
    private Product product;


}