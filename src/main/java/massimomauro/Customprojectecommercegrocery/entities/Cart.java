package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "created_date")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customerr_id")
    private Customer customer;

    private double quantity;


    //private String msg; //creare un oggetto messaggi
}
