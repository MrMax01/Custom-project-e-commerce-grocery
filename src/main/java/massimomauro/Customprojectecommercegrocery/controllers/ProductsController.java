package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;
    @GetMapping("")
    public Page<Product> getAllProduct(@RequestParam(defaultValue = "0")int page ,
                                    @RequestParam(defaultValue = "10")int size,
                                    @RequestParam(defaultValue = "id")String order,
                                    @RequestParam(defaultValue = "true")boolean ascending){
        return productsService.getProducts(page , size , order , ascending);
    }
    @GetMapping("/{supplierId}")
    public List<Product> getProductsBySupplier(@PathVariable UUID supplierId) {
        return productsService.getProductsBySupplier(supplierId);
    }
}
