package ua.artcode.billapp.repository;

import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Company;

import java.util.List;


@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {


    Company findCompanyByCompanyName(String name);
}
