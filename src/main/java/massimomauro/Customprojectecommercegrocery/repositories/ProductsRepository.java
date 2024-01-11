package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductsRepository extends JpaRepository<Product, UUID> {
    List<Product> findBySupplierId(UUID supplierId);
    List<Product> findAllBySupplier(Supplier supplier);
    Page<Product> findByCategory(ProductCategory category, Pageable pageable);
    Optional<Product> findByIdAndSupplierEmail(UUID productId, String supplierEmail);
    List<Product> findByNameStartingWithIgnoreCase(String name);
    @Query("SELECT p FROM Product p JOIN FETCH p.supplier WHERE p.id = :productId")
    Product getProductByIdWithSupplier(@Param("productId") UUID productId);
}
