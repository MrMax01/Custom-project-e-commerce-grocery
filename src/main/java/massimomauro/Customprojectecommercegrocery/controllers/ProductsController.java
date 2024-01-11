package massimomauro.Customprojectecommercegrocery.controllers;

import com.cloudinary.utils.ObjectUtils;
import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductCategory;
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
                                       @RequestParam(defaultValue = "true")boolean ascending,
                                       @RequestParam(name = "category", required = false) ProductCategory category,
                                       @RequestParam(defaultValue ="false") boolean orderByDate){


        return productsService.getProducts(page, size, order, ascending, orderByDate, category);

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

    @GetMapping("/nomeProdotto")
    public List<Product> findByNameStartingWith(@RequestParam String name){
        return productsService.findByNameStartingWith(name);

    }

}
