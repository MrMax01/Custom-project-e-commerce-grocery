package massimomauro.Customprojectecommercegrocery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import massimomauro.Customprojectecommercegrocery.entities.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private OrderStatus orderStatus;
    private LocalDate created_at;

    private double totalCost;
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;


    //private String msg; //creare un oggetto messaggi
}
