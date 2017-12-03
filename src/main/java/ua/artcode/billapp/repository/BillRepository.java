package ua.artcode.billapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.User;

import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */
@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill,Long> {

    List<Bill> findByCustomerAndBillStatus(User customer, BillStatus billStatus);

}
