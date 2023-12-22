package massimomauro.Customprojectecommercegrocery.payloads;

import jakarta.validation.constraints.*;

public record NewEntrepreneurDTO(
        @NotEmpty(message = "il nome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String name,

        @NotNull(message = "la partita iva è un campo obbligatorio!")
        @Digits(integer = 11, fraction = 0, message = "La partita iva deve essere lunga esattamente 11 cifre")
        Long partitaIva,

        @NotEmpty(message = "la mail è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])\\S{8,15}$", message = "Your password must meet the following criteria:" +
                " at least 8 characters long, no more than 15 characters, include uppercase and lowercase letters, contain at least one number, " +
                "and at least one special character (e.g., @, #, $, %, etc.). Spaces are not allowed.")
        String password,

        @NotNull
        @Positive
        Long telephone,

        @NotEmpty(message = "il cognome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String surname,

        @NotEmpty(message = "il cognome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String company_name,

        @NotEmpty(message = "l'indirizzo è obbligatorio")
        String region,

        @NotEmpty(message = "l'indirizzo è obbligatorio")
        String city,
        @NotEmpty(message = "l'indirizzo è obbligatorio")
        String via
) {
}
