package ua.artcode.billapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Customer;

import java.util.List;

/**
 * Created by Serhii Kolomiiets on 09.12.2017.
 */
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> getCustomerByName(@Param("name") String name);
}
