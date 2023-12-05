package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="suppliers")
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue
    private UUID id;
    private String avatar;
    private String name;
    private String surname;
    private String company_name;
    private long telephone;
    private String address;//prendere in considerazione di creare un oggetto address
    private String email;
    private long partita_iva;


    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

}
