package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.exceptions.BadRequestException;
import massimomauro.Customprojectecommercegrocery.payloads.NewEntrepreneurDTO;
import massimomauro.Customprojectecommercegrocery.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    AuthService authService;
    @PostMapping("/supplier")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier saveSupplier(@RequestBody @Validated NewEntrepreneurDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerSupplier(body);
            }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }

        }
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody @Validated NewEntrepreneurDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerCustomer(body);
            }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }

        }
    }
}
