package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import massimomauro.Customprojectecommercegrocery.entities.enums.Role;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String partiata_iva;
    private Role role;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
