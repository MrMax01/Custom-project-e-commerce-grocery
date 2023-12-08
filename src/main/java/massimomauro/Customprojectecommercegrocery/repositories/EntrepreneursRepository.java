package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EntrepreneursRepository extends JpaRepository<Entrepreneur, UUID> {
    Optional<Customer> findByEmail(String email);
}
