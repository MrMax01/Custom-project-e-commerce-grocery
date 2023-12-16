package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.services.CustomersService;
import massimomauro.Customprojectecommercegrocery.services.EntrepreneursService;
import massimomauro.Customprojectecommercegrocery.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/me")
public class CurrentUserController {
    @Autowired
    EntrepreneursService entrepreneursService;


    @GetMapping("")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){


        return currentUser;
    };


/*
    @PutMapping("")
    @PreAuthorize("hasAuthority('SUPPLIER')")
    public UserDetails getProfile(@PathVariable UUID id, @RequestBody Supplier body){
        return suppliersService.findByIdAndUpdate(id , body);
    }

 */
@PutMapping("")
public UserDetails updateProfile(@AuthenticationPrincipal UserDetails currentUser, @RequestBody Entrepreneur body) {
    // Aggiorna il profilo dell'utente con i dati forniti nella richiesta
    UserDetails updatedUser = null;


        updatedUser = entrepreneursService.updateEntrepreneursProfile(currentUser.getUsername(), body);


    // Restituisci il profilo aggiornato
    return updatedUser;
}
}
