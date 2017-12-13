package ua.artcode.billapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Company;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}
