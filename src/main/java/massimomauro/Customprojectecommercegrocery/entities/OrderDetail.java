package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name="order_details")
@Getter
public class OrderDetail {
    @Id
    @GeneratedValue
    private UUID id;
    private double total;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
