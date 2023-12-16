package massimomauro.Customprojectecommercegrocery.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductStatus;
import massimomauro.Customprojectecommercegrocery.exceptions.BadRequestException;
import massimomauro.Customprojectecommercegrocery.exceptions.NotFoundException;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;
import massimomauro.Customprojectecommercegrocery.repositories.ProductsRepository;
import massimomauro.Customprojectecommercegrocery.repositories.SuppliersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    SuppliersService suppliersService;
    @Autowired
    SuppliersRepository suppliersRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Page<Product> getProducts(int page, int size, String orderBy , boolean ascending ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));


        if (!ascending) pageable  = PageRequest.of(page, size, Sort.by(orderBy).descending());


        return productsRepository.findAll(pageable);
    }
    public Product findById(UUID uuid) throws NotFoundException {

        return productsRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void findProductByUUIDAndDelete(UUID uuid) throws NotFoundException {
        Product foundProduct = this.findById(uuid);
        productsRepository.delete(foundProduct);
    }
    public String imageUpload(UUID id, MultipartFile file) throws NotFoundException, IOException {
        Product found = this.findById(id);
        String img = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setPhoto(img);
        productsRepository.save(found);
        return found.getPhoto();
    }
    public Product saveProduct(NewProductDTO body, String email){



        Product newProduct = new Product();

        newProduct.setName(body.name());
        newProduct.setCategory(body.category());
        newProduct.setDescription(body.description());
        newProduct.setUnit_price(body.unit_price());
        newProduct.setQuantity(body.quantity());
        newProduct.setSupplier(suppliersService.findByEmail(email));
        newProduct.setProduct_status(ProductStatus.DISPONIBILE);

        return productsRepository.save(newProduct);
    }
    /*
    public List<Product> getProductsBySupplier(String ownerEmail) {
        return productsRepository.findBySupplier(ownerEmail);
    }

     */
}
