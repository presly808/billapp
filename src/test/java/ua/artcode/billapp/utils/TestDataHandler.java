package ua.artcode.billapp.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import ua.artcode.billapp.repository.CustomerRepository;
import ua.artcode.billapp.security.model.JwtUser;
import ua.artcode.billapp.service.CompanyService;
import ua.artcode.billapp.service.CustomerService;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TestDataHandler {

    protected final String AUTH_KEY = "Authorization";
    protected final String AUTH_VALUE_TOKEN = "Token ";

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
    protected String token;


    protected void initData() throws Exception {
        //Getting test data from JSON files
        testCompanies = mapper.readValue(this.getClass().getResourceAsStream("/company.json"), new TypeReference<List<Company>>() {});
        testCustomers = mapper.readValue(this.getClass().getResourceAsStream("/customers.json"), new TypeReference<List<Customer>>() {});
        testBills = mapper.readValue(this.getClass().getResourceAsStream("/bills.json"), new TypeReference<List<Bill>>() {});
        //Save data into repositories
        companyRepository.save(testCompanies);
        customerRepository.save(testCustomers);
        billRepository.save(testBills);

        JwtUser jwtUser = mapper.readValue(this.getClass().getResourceAsStream("/token.json"), JwtUser.class);

        String requestBody = mapper.writeValueAsString(jwtUser);
        MvcResult result = mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        token = result.getResponse().getContentAsString();
        assertNotNull(token);
    }

    protected void clearRepositories() {
        billRepository.deleteAll();
        companyRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
