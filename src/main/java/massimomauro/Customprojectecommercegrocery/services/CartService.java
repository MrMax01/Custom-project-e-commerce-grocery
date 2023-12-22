package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Cart;
import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.exceptions.NotFoundException;
import massimomauro.Customprojectecommercegrocery.exceptions.UnauthorizedException;
import massimomauro.Customprojectecommercegrocery.payloads.AddToCartDto;
import massimomauro.Customprojectecommercegrocery.repositories.CartRepository;
import massimomauro.Customprojectecommercegrocery.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    ProductsService productsService;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomersService customersService;

    public void addToCart(AddToCartDto addToCartDto, String email) {

        LocalDate today= LocalDate.now();
        // validate if the product id is valid
        Product product = productsService.findById(addToCartDto.productId());
        Customer customer = customersService.findByEmail(email);
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setCustomer(customer);
        cart.setQuantity(addToCartDto.quantity());
        cart.setCreatedAt(today);


        // save the cart
        cartRepository.save(cart);

    }



    public void deleteCartItem(UUID cartItemId, String email) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new NotFoundException("cart item id is invalid: " + cartItemId);
        }
        Customer customer = customersService.findByEmail(email);
        Cart cart = optionalCart.get();

        if (cart.getCustomer() != customer) {
            throw  new UnauthorizedException("cart item does not belong to user: " +cartItemId);
        }

        cartRepository.delete(cart);


    }

}