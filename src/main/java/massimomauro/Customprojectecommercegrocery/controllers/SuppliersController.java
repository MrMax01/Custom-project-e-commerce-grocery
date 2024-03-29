package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Cart;
import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.services.EntrepreneursService;
import massimomauro.Customprojectecommercegrocery.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
public class SuppliersController {
    @Autowired
    private SuppliersService suppliersService;


    @GetMapping("")
    public Page<Supplier> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                     @RequestParam(defaultValue = "10")int size,
                                     @RequestParam(defaultValue = "id")String order,
                                     @RequestParam(defaultValue = "true")boolean ascending){
        return suppliersService.getSuppliers(page , size , order , ascending);
    }

   @GetMapping("/{id}")
    public Supplier findById(@PathVariable UUID id){
        return suppliersService.findById(id);
    }


    @GetMapping("/products")
    public List<Product> getMyProductList(@AuthenticationPrincipal UserDetails currentUser) {
        return suppliersService.getProductList( currentUser.getUsername() );
    }

/*
    @PatchMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadPicture(@PathVariable UUID id, @RequestParam("avatar") MultipartFile file) throws IOException {
        return usersService.imageUpload(id, file);
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        usersService.findByIdAndDelete(id);
    }
*/


}
