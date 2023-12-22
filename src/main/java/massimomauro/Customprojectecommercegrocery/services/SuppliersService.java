package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Entrepreneur;
import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.exceptions.NotFoundException;
import massimomauro.Customprojectecommercegrocery.repositories.EntrepreneursRepository;
import massimomauro.Customprojectecommercegrocery.repositories.SuppliersRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SuppliersService {
    @Autowired
    SuppliersRepository suppliersRepository;
    @Autowired
    EntrepreneursRepository entrepreneursRepository;
    public Page<Supplier> getSuppliers(int page, int size, String orderBy , boolean ascending ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));


        if (!ascending) pageable  = PageRequest.of(page, size, Sort.by(orderBy).descending());


        return suppliersRepository.findAll(pageable);
    }

    public Supplier findById(UUID id) throws NotFoundException {
        return suppliersRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }
    public Supplier findByEmail(String email) throws NotFoundException{
        return suppliersRepository.findByEmail(email).orElseThrow(()->new NotFoundException(email) );
    }

    /*
    public void findByIdAndDelete(UUID id) throws NotFoundException{
        User found = this.findById(id);
        usersRepository.delete(found);
    }
    */




}
