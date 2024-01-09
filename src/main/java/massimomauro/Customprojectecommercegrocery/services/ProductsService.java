package massimomauro.Customprojectecommercegrocery.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductCategory;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductStatus;
import massimomauro.Customprojectecommercegrocery.exceptions.BadRequestException;
import massimomauro.Customprojectecommercegrocery.exceptions.NotFoundException;
import massimomauro.Customprojectecommercegrocery.payloads.NewProductDTO;
import massimomauro.Customprojectecommercegrocery.payloads.UpdateProductDTO;
import massimomauro.Customprojectecommercegrocery.repositories.ProductsRepository;
import massimomauro.Customprojectecommercegrocery.repositories.SuppliersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

    public Page<Product> getProducts(int page, int size, String orderBy , boolean ascending, boolean orderByDate, ProductCategory category) {
        Pageable pageable;

        if (orderByDate) {
            pageable = PageRequest.of(page, size, Sort.by("publicatedAt").descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(orderBy));
            if (!ascending) {
                pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
            }
        }
        if (category != null) {
            return productsRepository.findByCategory(category, pageable);
        } else {
            return productsRepository.findAll(pageable);
        }

    }
    public Product findById(UUID uuid) throws NotFoundException {

        return productsRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void findProductByUUIDAndDelete(UUID uuid) throws NotFoundException {
        Product foundProduct = this.findById(uuid);

        productsRepository.delete(foundProduct);
    }
    public String imageUpload(UUID id, MultipartFile file, String email) throws NotFoundException, IOException {

        Product found = this.findById(id);
        if(!isProductOwnedBySupplier(id, email)){
            return null;
        }
        String img = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setPhoto(img);
        productsRepository.save(found);
        return found.getPhoto();
    }
    public Product saveProduct(NewProductDTO body, String email){



        Product newProduct = new Product();
        LocalDate today = LocalDate.now();

        newProduct.setName(body.name());
        newProduct.setCategory(body.category());
        newProduct.setDescription(body.description());
        newProduct.setUnit_price(body.unit_price());
        newProduct.setQuantity(body.quantity());
        newProduct.setPublicatedAt(today);
        newProduct.setPhoto("http://ui-avatars.com/api/?name=" + body.name().trim().replace(" " , "") + "+" + body.name().trim().replace(" " , ""));
        newProduct.setSupplier(suppliersService.findByEmail(email));
        newProduct.setProduct_status(ProductStatus.DISPONIBILE);

        return productsRepository.save(newProduct);
    }
    public Product findProductByUUIDAndUpdate(UpdateProductDTO body, UUID id, String email) throws NotFoundException {
        Product foundProduct = this.findById(id);
        if(!isProductOwnedBySupplier(id, email)){
            return null;
        }
        foundProduct.setName(body.name());
        foundProduct.setCategory(body.category());
        foundProduct.setDescription(body.description());
        foundProduct.setUnit_price(body.unit_price());
        foundProduct.setQuantity(body.quantity());
        foundProduct.setProduct_status(body.product_status());
        return productsRepository.save(foundProduct);
    }


    public List<Product> getProductsBySupplier(UUID supplierId) {
        return productsRepository.findBySupplierId(supplierId);
    }
    public boolean isProductOwnedBySupplier(UUID productId, String supplierEmail) {
        // Verifica se il prodotto esiste e se il fornitore associato è quello autenticato
        return productsRepository.findByIdAndSupplierEmail(productId, supplierEmail).isPresent();
    }
}
