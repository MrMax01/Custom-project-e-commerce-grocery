package massimomauro.Customprojectecommercegrocery.repositories;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Order;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByCustomer(Customer customer);
    List<Order> findAllBySupplier(Supplier supplier);
}
