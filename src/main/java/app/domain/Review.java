package app.domain;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(min = 0, max = 5)
    @Column(name = "punctuation", nullable = false)
    private double punctuation;

    @NotNull
    @Column(name = "comment" , nullable = false)
    private String comment;

    ///Relations
    @ManyToOne
    private Product product;

    public void updateFrom(Review review){
        this.punctuation = review.getPunctuation();
        this.comment = review.getComment();
    }

}