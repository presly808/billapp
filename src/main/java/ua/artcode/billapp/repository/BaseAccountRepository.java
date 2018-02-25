package ua.artcode.billapp.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.BaseAccount;
import ua.artcode.billapp.model.Company;

@Repository
public interface BaseAccountRepository extends PagingAndSortingRepository<BaseAccount, Long> {

    //BaseAccount findBaseAccountByBaseAccountPhone(String phone);
}
