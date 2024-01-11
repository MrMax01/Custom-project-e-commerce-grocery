
package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.*;

import massimomauro.Customprojectecommercegrocery.entities.enums.OrderStatus;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductStatus;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;
import massimomauro.Customprojectecommercegrocery.repositories.OrdersRepository;
import massimomauro.Customprojectecommercegrocery.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CustomersService customersService;
    @Autowired
    SuppliersService suppliersService;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CartService cartService;
    public List<Order> getOrdersByCustomer(String email) {
        Customer me= customersService.findByEmail(email);
        return ordersRepository.findAllByCustomer(me);
    }
    public List<Order> getOrdersBySupplier(String email) {
        Supplier me= suppliersService.findByEmail(email);
        return ordersRepository.findAllBySupplier(me);
    }

    public void addOrder (String customerEmail, Cart cart){
        LocalDate today= LocalDate.now();
        Order myOrder = new Order();
        Customer me= customersService.findByEmail(customerEmail);
        Product product = productsRepository.getProductByIdWithSupplier(cart.getProduct().getId());
        Supplier supplier = product.getSupplier();
        myOrder.setOrderStatus(OrderStatus.IN_PREPARAZIONE);
        myOrder.setProduct(cart.getProduct());
        myOrder.setCustomer(me);
        myOrder.setSupplier(supplier);
        myOrder.setCreated_at(today);
        myOrder.setQuantity(cart.getQuantity());
        myOrder.setTotalCost(cart.getProduct().getUnit_price()* cart.getQuantity());
        if(cart.getProduct().getQuantity()> cart.getQuantity()){
            ordersRepository.save(myOrder);
            cartService.deleteCartItem(cart.getId(), me.getEmail() );
        }

    }



}





