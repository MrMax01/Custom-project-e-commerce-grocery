package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.exceptions.NotFoundException;
import massimomauro.Customprojectecommercegrocery.payloads.EntrepreneurUpdateDTO;
import massimomauro.Customprojectecommercegrocery.repositories.EntrepreneursRepository;
import massimomauro.Customprojectecommercegrocery.repositories.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EntrepreneursService {
    @Autowired
    EntrepreneursRepository entrepreneursRepository;

    public Entrepreneur findById(UUID id) throws NotFoundException {
        return entrepreneursRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }
    public Entrepreneur findByEmail(String email){
        return entrepreneursRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

    public Entrepreneur updateEntrepreneursProfile(String email, EntrepreneurUpdateDTO body) throws NotFoundException{
        Entrepreneur found = entrepreneursRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));

        found.setName(body.name());
        found.setSurname(body.surname());

        found.setEmail(body.email());

        found.setPartita_iva(body.partitaIva());
        found.setTelephone(body.telephone());
        found.setCompany_name(body.company_name());
        found.setAddress(body.address());
        if (found.getAvatar().equals("http://ui-avatars.com/api/?name="+ found.getSurname().trim().replace(" " , "") + "+" + found.getName().trim().replace(" " , ""))){
            found.setAvatar("http://ui-avatars.com/api/?name=" + found.getSurname().trim().replace(" " , "") + "+" + body.name().trim().replace(" ", ""));
        }


        return entrepreneursRepository.save(found);
    }

}
