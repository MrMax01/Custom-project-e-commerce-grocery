package massimomauro.Customprojectecommercegrocery.services;

import massimomauro.Customprojectecommercegrocery.entities.Supplier;
import massimomauro.Customprojectecommercegrocery.repositories.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SuppliersService {
    @Autowired
    SuppliersRepository suppliersRepository;
    public Page<Supplier> getSuppliers(int page, int size, String orderBy , boolean ascending ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));


        if (!ascending) pageable  = PageRequest.of(page, size, Sort.by(orderBy).descending());


        return suppliersRepository.findAll(pageable);
    }
}
