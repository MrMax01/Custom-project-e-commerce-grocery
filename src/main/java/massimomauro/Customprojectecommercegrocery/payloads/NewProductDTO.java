package massimomauro.Customprojectecommercegrocery.payloads;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import massimomauro.Customprojectecommercegrocery.entities.enums.ProductCategory;
import massimomauro.Customprojectecommercegrocery.entities.enums.UnitOfMeasure;

public record NewProductDTO(
        @NotEmpty(message = "il nome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String name,
        @NotEmpty(message = "la categoria è un campo obbligatorio!")
        ProductCategory category,
        @NotEmpty(message = "la categoria è un campo obbligatorio!")
        UnitOfMeasure unitOfMeasure,
        @NotEmpty(message = "inserisci una breve descrizione del prodotto!")
        @Size(min = 3, message = "la descrizione essere lunga almeno 3 caratteri")
        String description,
        @NotNull(message = "il prezzo è un campo obbligatorio!")
        @Size(min=0)
        Double unit_price,
        @NotNull(message = "la quantitò è un campo obbligatorio!")
        @Size(min=0)
        Double quantity

) {
}
