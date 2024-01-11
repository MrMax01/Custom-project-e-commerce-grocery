package massimomauro.Customprojectecommercegrocery.payloads;

import jakarta.validation.constraints.NotNull;
import massimomauro.Customprojectecommercegrocery.entities.Cart;
import massimomauro.Customprojectecommercegrocery.entities.Product;

import java.util.List;

public record OrderDTO(
        @NotNull List<Cart> products
        ) {
}
