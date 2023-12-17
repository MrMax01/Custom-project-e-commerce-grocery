package massimomauro.Customprojectecommercegrocery.controllers;

import com.cloudinary.utils.ObjectUtils;
import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.services.AuthService;
import massimomauro.Customprojectecommercegrocery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;
    @Autowired
    AuthService authService;
    @GetMapping("")
    public Page<Product> getAllProduct(@RequestParam(defaultValue = "0")int page ,
                                    @RequestParam(defaultValue = "10")int size,
                                    @RequestParam(defaultValue = "id")String order,
                                    @RequestParam(defaultValue = "true")boolean ascending){
        return productsService.getProducts(page , size , order , ascending);
    }
    @GetMapping("/{id}")
    public Product findById(@PathVariable UUID id) {
        return productsService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPPLIER') ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        authService.checkProductOwner(id);
        productsService.findProductByUUIDAndDelete(id);
    }

    @GetMapping("/suppliers/{supplierId}")
    public List<Product> getProductsBySupplier(@PathVariable UUID supplierId) {
        return productsService.getProductsBySupplier(supplierId);
    }
    @PatchMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('SUPPLIER')")
    public String uploadPicture(@PathVariable UUID id, @RequestParam("avatar")MultipartFile file) throws IOException {
        authService.checkProductOwner(id);
        return productsService.imageUpload(id, file);
    }


}
