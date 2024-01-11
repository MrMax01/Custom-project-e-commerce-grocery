package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Cart;
import massimomauro.Customprojectecommercegrocery.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    List<Cart> findAllByCustomer(Customer customer);
}