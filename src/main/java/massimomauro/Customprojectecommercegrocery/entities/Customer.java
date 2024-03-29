package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import massimomauro.Customprojectecommercegrocery.entities.enums.Role;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="customers")


public class Customer extends Entrepreneur {


    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;



}
