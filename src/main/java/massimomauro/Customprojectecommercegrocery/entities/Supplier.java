package massimomauro.Customprojectecommercegrocery.entities;

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



    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

}
