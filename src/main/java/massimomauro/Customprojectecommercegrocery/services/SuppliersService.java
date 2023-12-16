package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.exceptions.NotFoundException;
import massimomauro.Customprojectecommercegrocery.repositories.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SuppliersService {
    @Autowired
    SuppliersRepository suppliersRepository;
    public Page<Supplier> getSuppliers(int page, int size, String orderBy , boolean ascending ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));


        if (!ascending) pageable  = PageRequest.of(page, size, Sort.by(orderBy).descending());


        return suppliersRepository.findAll(pageable);
    }

    public Supplier findById(UUID id) throws NotFoundException {
        return suppliersRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }

    /*
    public void findByIdAndDelete(UUID id) throws NotFoundException{
        User found = this.findById(id);
        usersRepository.delete(found);
    }
    */


    public Supplier findByIdAndUpdate(UUID id, Supplier body) throws NotFoundException{
        Supplier found = this.findById(id);

        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        if (found.getAvatar().equals("http://ui-avatars.com/api/?name="+ found.getSurname().trim().replace(" " , "") + "+" + found.getName().trim().replace(" " , ""))){
            found.setAvatar("http://ui-avatars.com/api/?name=" + found.getSurname().trim().replace(" " , "") + "+" + body.getName().trim().replace(" ", ""));
        }


        return suppliersRepository.save(found);
    }


}
