package massimomauro.Customprojectecommercegrocery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import massimomauro.Customprojectecommercegrocery.entities.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="suppliers")

public class Supplier extends Entrepreneur{


    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

}
