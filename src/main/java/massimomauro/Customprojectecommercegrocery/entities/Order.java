package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;

import massimomauro.Customprojectecommercegrocery.entities.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private OrderStatus orderStatus;
    private LocalDate created_at;
    /*
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> OrderDetails;

     */
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    //private String msg; //creare un oggetto messaggi
}
