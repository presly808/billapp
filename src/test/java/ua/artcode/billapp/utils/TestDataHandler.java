package ua.artcode.billapp.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import ua.artcode.billapp.repository.CustomerRepository;
import ua.artcode.billapp.service.CompanyService;
import ua.artcode.billapp.service.CustomerService;

import java.io.IOException;
import java.util.List;

@AutoConfigureMockMvc
public class TestDataHandler {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected BillRepository billRepository;

    @Autowired
    protected CompanyService companyService;
    @Autowired
    protected CustomerService customerService;

    protected List<Company> testCompanies;
    protected List<Customer> testCustomers;
    protected List<Bill> testBills;


    protected void initData() throws IOException {
        //Getting test data from JSON files
        testCompanies = mapper.readValue(this.getClass().getResourceAsStream("/company.json"), new TypeReference<List<Company>>() {});
        testCustomers = mapper.readValue(this.getClass().getResourceAsStream("/customers.json"), new TypeReference<List<Customer>>() {});
        testBills = mapper.readValue(this.getClass().getResourceAsStream("/bills.json"), new TypeReference<List<Bill>>() {});
        //Save data into repositories
        companyRepository.save(testCompanies);
        customerRepository.save(testCustomers);
        billRepository.save(testBills);
    }

    protected void clearRepositories() {
        billRepository.deleteAll();
        companyRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
