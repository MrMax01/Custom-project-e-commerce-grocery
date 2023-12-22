package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Customer;
import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.entities.enums.Role;
import massimomauro.Customprojectecommercegrocery.exceptions.BadRequestException;
import massimomauro.Customprojectecommercegrocery.exceptions.UnauthorizedException;
import massimomauro.Customprojectecommercegrocery.payloads.EntrepreneurLoginDTO;
import massimomauro.Customprojectecommercegrocery.payloads.NewEntrepreneurDTO;
import massimomauro.Customprojectecommercegrocery.repositories.EntrepreneursRepository;
import massimomauro.Customprojectecommercegrocery.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private EntrepreneursService entrepreneursService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private EntrepreneursRepository entrepreneursRepository;
    @Autowired
    ProductsService productsService;

    public String authenticateUser(EntrepreneurLoginDTO body){
        // 1. Verifichiamo che l'email dell'utente sia nel db
        Entrepreneur user = entrepreneursService.findByEmail(body.email());

        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(bcrypt.matches(body.password(), user.getPassword())) {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public Supplier registerSupplier(NewEntrepreneurDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        entrepreneursRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        Supplier newUser = new Supplier();

        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setPartita_iva(body.partitaIva());

        newUser.setTelephone(body.telephone());
        newUser.setCompany_name(body.company_name());
        newUser.setRegion(body.region());
        newUser.setCity(body.city());
        newUser.setVia(body.via());
        newUser.setAvatar("http://ui-avatars.com/api/?name=" + body.surname().trim().replace(" " , "") + "+" + body.name().trim().replace(" " , ""));

        newUser.setRole(Role.SUPPLIER);

        Supplier savedUser = entrepreneursRepository.save(newUser);

        return savedUser;
    }

    public Customer registerCustomer(NewEntrepreneurDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        entrepreneursRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        Customer newUser = new Customer();

        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());

        newUser.setPartita_iva(body.partitaIva());
        newUser.setTelephone(body.telephone());
        newUser.setCompany_name(body.company_name());
        newUser.setRegion(body.region());
        newUser.setCity(body.city());
        newUser.setVia(body.via());

        newUser.setAvatar("http://ui-avatars.com/api/?name=" + body.surname().trim().replace(" " , "") + "+" + body.name().trim().replace(" " , ""));

        newUser.setRole(Role.CUSTOMER);

        Customer savedUser = entrepreneursRepository.save(newUser);

        return savedUser;
    }

    public void checkProductOwner(UUID productId) {
        // Otteniamo l'email dell'utente autenticato
        String supplierEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(supplierEmail);
        // Controlliamo se il fornitore associato al prodotto è l'utente autenticato
        if (!productsService.isProductOwnedBySupplier(productId, supplierEmail)) {
            throw new UnauthorizedException("You do not have permission to delete this product.");
        }
    }
}
