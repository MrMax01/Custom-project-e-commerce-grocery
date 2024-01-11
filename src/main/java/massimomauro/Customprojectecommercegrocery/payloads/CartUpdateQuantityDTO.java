package massimomauro.Customprojectecommercegrocery.payloads;

import jakarta.validation.constraints.NotNull;

public record CartUpdateQuantityDTO(@NotNull double quantity) {
}
