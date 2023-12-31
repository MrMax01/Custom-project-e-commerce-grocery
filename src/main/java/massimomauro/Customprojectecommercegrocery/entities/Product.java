package massimomauro.Customprojectecommercegrocery.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductCategory;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private ProductCategory category;//da vedere se metterlo come ENUM o no
    private String description;
    private String photo;
    private String quality;//come posso attestare la qualità del prodotto
    private LocalDate publicatedAt;
    private  double unit_price;
    private double quantity;
    @Enumerated(EnumType.STRING)
    private ProductStatus product_status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductReview> reviews;
/*
     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> OrderDetails;

 */

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cart;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
