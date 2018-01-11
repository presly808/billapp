package ua.artcode.billapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.model.Customer;

import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */
@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
    List<Bill> findByCustomerAndBillStatus(Customer customer, BillStatus billStatus);

    List<Bill> findByProviderAndBillStatus(Company company, BillStatus billStatus);
}
