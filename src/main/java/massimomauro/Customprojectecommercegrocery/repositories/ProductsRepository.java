package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductsRepository extends JpaRepository<Product, UUID> {
    List<Product> findBySupplierId(UUID supplierId);
    Optional<Product> findByIdAndSupplierEmail(UUID productId, String supplierEmail);
}
