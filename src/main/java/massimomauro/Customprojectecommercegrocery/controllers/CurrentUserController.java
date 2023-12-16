package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.payloads.EntrepreneurUpdateDTO;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;
import massimomauro.Customprojectecommercegrocery.services.CustomersService;
import massimomauro.Customprojectecommercegrocery.services.EntrepreneursService;
import massimomauro.Customprojectecommercegrocery.services.ProductsService;
import massimomauro.Customprojectecommercegrocery.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/me")
public class CurrentUserController {
    @Autowired
    EntrepreneursService entrepreneursService;

    @Autowired
    ProductsService productsService;

    @GetMapping("")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){


        return currentUser;
    };



@PutMapping("")
    public UserDetails updateProfile(@AuthenticationPrincipal UserDetails currentUser, @RequestBody EntrepreneurUpdateDTO body) {
        // Aggiorna il profilo dell'utente con i dati forniti nella richiesta



            UserDetails updatedUser = entrepreneursService.updateEntrepreneursProfile(currentUser.getUsername(), body);


        // Restituisci il profilo aggiornato
        return updatedUser;
    }

    @PostMapping("/product")
    @PreAuthorize("hasAuthority('SUPPLIER')")
    public Product saveProduct(@AuthenticationPrincipal UserDetails currentUser, @RequestBody NewProductDTO body){
        return productsService.saveProduct(body, currentUser.getUsername());
    }
    /*
    @GetMapping("/product")
    public List<Product> saveProduct(@AuthenticationPrincipal UserDetails currentUser){
        return productsService.getProductsBySupplier(currentUser.getUsername());
    }
    */


}
