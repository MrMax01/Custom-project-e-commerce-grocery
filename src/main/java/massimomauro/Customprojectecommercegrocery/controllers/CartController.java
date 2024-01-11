package massimomauro.Customprojectecommercegrocery.controllers;

import com.cloudinary.api.ApiResponse;
import massimomauro.Customprojectecommercegrocery.entities.Cart;
import massimomauro.Customprojectecommercegrocery.entities.Product;
import massimomauro.Customprojectecommercegrocery.payloads.AddToCartDto;
import massimomauro.Customprojectecommercegrocery.payloads.CartUpdateQuantityDTO;
import massimomauro.Customprojectecommercegrocery.payloads.EntrepreneurLoginDTO;
import massimomauro.Customprojectecommercegrocery.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
     CartService cartService;




    // post cart api
    @PostMapping("/add")
    public Cart addToCart(@AuthenticationPrincipal UserDetails currentUser, @RequestBody AddToCartDto body) {
        return cartService.addToCart(body, currentUser.getUsername() );
    }


    // get all cart items for a user

    @GetMapping("")
    public List<Cart> getMyCartList(@AuthenticationPrincipal UserDetails currentUser) {
        return cartService.getCartList( currentUser.getUsername() );
    }
    // delete a cart item for a user

    @DeleteMapping("/{cartItemId}")
    public void deleteCartItem(@AuthenticationPrincipal UserDetails currentUser, @PathVariable("cartItemId") UUID itemId) {

        cartService.deleteCartItem(itemId, currentUser.getUsername());

    }

    @PutMapping("/{id}")
    public Cart putQuantityCart(@AuthenticationPrincipal UserDetails currentUser, @PathVariable UUID id, @RequestBody CartUpdateQuantityDTO body){
        return cartService.updateCart(id , body, currentUser.getUsername());
    }
    /*
    @GetMapping("/customer/{supplierId}")
    public List<Product> getProductsBySupplier(@PathVariable UUID supplierId) {
        return cartService.getProductsByCustomer(supplierId);
    }

     */

}