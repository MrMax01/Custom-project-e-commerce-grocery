/*
package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Order;

import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;

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

    @GetMapping("/customer/{customerId}")
    public List<Order> getProductsBySupplier(@PathVariable UUID customerId) {
        return ordersService.getOrdersByCustomer(customerId);
    }


    @PostMapping("/details")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderDetail saveProduct(@AuthenticationPrincipal UserDetails currentUser, @RequestBody NewProductDTO body){
        return ordersService.saveOrder(body, currentUser.getUsername());
    }


}

 */
