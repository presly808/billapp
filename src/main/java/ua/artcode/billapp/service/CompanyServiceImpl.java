package ua.artcode.billapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<Bill> getOpenedBills(Company company) throws AppException {
        LOGGER.info("Company with id " + company.getId() + " is getting its opened bills.");
        return billRepository.findByProviderAndBillStatus(company, BillStatus.OPENED);
    }
}
