package ua.artcode.billapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.repository.*;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final BillRepository billRepository;

    private final UserRepository userRepository;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              BillRepository billRepository,
                              UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Bill> getOpenedBills(Company company) throws AppException {
        LOGGER.info("Company with id " + company.getId() + " is getting its opened bills.");
        return billRepository.findByProviderAndBillStatus(company, BillStatus.OPENED);

    }

    @Override
    public Bill createBill(Bill bill) throws AppException {
        LOGGER.info("Bill " + bill.getTitle() + " start created");
        bill.setStart(LocalDateTime.now());
        bill.setBillId(UUID.randomUUID().toString());
        bill.setBillStatus(BillStatus.OPENED);
        billRepository.save(bill);
        return bill;
    }

}
