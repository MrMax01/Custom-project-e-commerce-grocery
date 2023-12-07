package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findByEmail(String email);
}
