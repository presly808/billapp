package ua.artcode.billapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.artcode.billapp.exception.BillApplicationException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final BillRepository billRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, BillRepository billRepository) {
        this.companyRepository = companyRepository;
        this.billRepository = billRepository;
    }


    @Override
    public List<Bill> getOpenedBills(Company company) throws BillApplicationException {
        LOGGER.info("Company with id " + company.getId() + " is getting its opened bills.");
        return billRepository.findByProviderAndBillStatus(company, BillStatus.OPENED);

    }

    @Override
    public Bill createBill(Bill bill) throws BillApplicationException {
        LOGGER.info("Bill " + bill.getTitle() + " start created");
        bill.setStart(LocalDateTime.now());
        bill.setBillId(UUID.randomUUID().toString());
        bill.setBillStatus(BillStatus.OPENED);
        billRepository.save(bill);
        return bill;
    }


    @Override
    public List<Bill> getClosedBills(Company company) throws BillApplicationException {
        LOGGER.info("Company with id " + company.getId() + " is getting its closed bills.");
        return billRepository.findByProviderAndBillStatus(company, BillStatus.CLOSED);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findOne(id);
    }
}
