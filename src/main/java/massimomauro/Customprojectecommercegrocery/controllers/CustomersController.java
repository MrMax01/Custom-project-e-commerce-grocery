package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    CustomersService customersService;

    @GetMapping("")
    public Page<Customer> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                     @RequestParam(defaultValue = "10")int size,
                                     @RequestParam(defaultValue = "id")String order,
                                     @RequestParam(defaultValue = "true")boolean ascending){
        return customersService.getSuppliers(page , size , order , ascending);
    }
}
