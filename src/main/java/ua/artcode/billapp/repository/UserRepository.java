package ua.artcode.billapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Customer;

/**
 * Created by serhii on 03.12.17.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<Customer, Long> {
}
