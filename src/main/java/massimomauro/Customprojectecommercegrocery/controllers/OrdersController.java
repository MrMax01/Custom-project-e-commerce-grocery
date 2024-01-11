
package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Cart;
import massimomauro.Customprojectecommercegrocery.entities.Order;

import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;

import massimomauro.Customprojectecommercegrocery.payloads.OrderDTO;
import massimomauro.Customprojectecommercegrocery.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    //@PreAuthorize("hasAuthority('CUSTOMER')")

    @GetMapping("")
    public List<Order> getProductsBySupplier(@AuthenticationPrincipal UserDetails currentUser) {
        if(currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("SUPPLIER"))){
            return ordersService.getOrdersBySupplier(currentUser.getUsername());
        }else{
            return ordersService.getOrdersByCustomer(currentUser.getUsername());
        }
    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void saveOrder(@AuthenticationPrincipal UserDetails currentUser, @RequestBody List<Cart> body){
        for (Cart cart : body) {
            ordersService.addOrder(currentUser.getUsername(), cart);
            // Assicurati che il tuo ordersService abbia un metodo saveOrder che accetti il nome utente del cliente e un prodotto.
        }
    }


}


