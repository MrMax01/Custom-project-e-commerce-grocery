package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="product_reviews")
@Getter
@Setter
public class ProductReview {
    @Id
    @GeneratedValue
    private UUID id;
    private double rating;
    private String comment;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

}
