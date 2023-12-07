package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CustomersRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
}
