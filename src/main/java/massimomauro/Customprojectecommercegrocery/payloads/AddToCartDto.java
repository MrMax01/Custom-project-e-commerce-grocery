package massimomauro.Customprojectecommercegrocery.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddToCartDto(
        @NotNull UUID productId,
        @NotNull Double quantity
) {
}
