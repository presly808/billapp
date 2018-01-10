package ua.artcode.billapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import ua.artcode.billapp.repository.CustomerRepository;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ObjectMapper mapper;

    private String expected;
    private String json;

    @Before
    public void setUp() throws IOException {
        List<Bill> bills = mapper.readValue(this.getClass().getResourceAsStream("/bills.json"), new TypeReference<List<Bill>>() {});

        Gson gson = new Gson();
        json = gson.toJson(bills.get(0));
        expected = "380932321223";

        List<Company> companies = mapper.readValue(this.getClass().getResourceAsStream("/company.json"), new TypeReference<List<Company>>() {});
        companyRepository.save(companies.get(0));

        List<Customer> customers = mapper.readValue(this.getClass().getResourceAsStream("/customers.json"), new TypeReference<List<Customer>>() {});
        customerRepository.save(customers.get(0));

        Bill closedBill = bills.get(1);
        closedBill.setCustomer(customers.get(0));
        closedBill.setProvider(companies.get(0));

        Bill openedBill = bills.get(2);
        openedBill.setCustomer(customers.get(0));
        openedBill.setProvider(companies.get(0));

        billRepository.save(closedBill);
        billRepository.save(openedBill);
    }

    @After
    public void tearDown() {
        billRepository.deleteAll();
        customerRepository.deleteAll();
        companyRepository.deleteAll();
        expected = null;
    }

    @Test
    public void getClosedBillsTest() throws Exception {
        String id = companyRepository.findCompanyByCompanyName("TestCompany").getId().toString();
        MvcResult result = mockMvc.perform(get("/get-closed-bills").param("id", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String str = result.getResponse().getContentAsString();
        assertTrue(str.contains(expected));
        assertNotNull(str);

    }

    @Test
    public void getOpenBillsTest() throws Exception {
        String id = companyRepository.findCompanyByCompanyName("TestCompany").getId().toString();
        MvcResult result = mockMvc.perform(get("/get-opened-bills").param("id", id))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String str = result.getResponse().getContentAsString();
        assertTrue(str.contains(expected));
        assertNotNull(str);
    }

    @Test
    public void shouldReturnCreatedBill() throws Exception {
        String expected = "some_bill";
        MvcResult result = mockMvc.perform(post("/create-bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk()).andReturn();
        String body = result.getResponse().getContentAsString();
        assertTrue(body.contains(expected));
        assertNotNull(body);
    }
}
