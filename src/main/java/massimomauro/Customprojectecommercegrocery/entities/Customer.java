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

    private String partiata_iva;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
