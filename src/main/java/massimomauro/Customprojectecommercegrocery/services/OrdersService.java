package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Order;
import massimomauro.Customprojectecommercegrocery.entities.OrderDetail;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductStatus;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;
import massimomauro.Customprojectecommercegrocery.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;
    public List<Order> getOrdersByCustomer(UUID CustomerId) {
        return ordersRepository.findByCustomerId(CustomerId);
    }
    public OrderDetail saveOrder(NewProductDTO body, String email){

        OrderDetail newOrder = new OrderDetail();

        return newOrder;
    }
}
