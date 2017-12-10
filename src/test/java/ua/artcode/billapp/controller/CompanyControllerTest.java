package ua.artcode.billapp.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.artcode.billapp.model.*;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import ua.artcode.billapp.repository.UserRepository;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;
    private Company company;

    @Before
    public void setUp(){

        Address address = new Address();
        address.setStreet("test");
        address.setNumber("test");
        address.setCity("test");


        Customer customer  = new Customer();
        customer.setActivated(true);
        customer.setName("Ivan");
        customer.setPhone("380932321203");
        customer.setPass("1234");

        this.company = new Company();
        Company company = this.company;
        company.setActivated(true);
        company.setAddress(address);
        company.setAdditionalInfo("TestInfo");
        company.setType("Shop");
        company.setCompanyName("TestCompany");
        company.setPhone("380932321223");
        company.setPass("1234");

        companyRepository.save(company);
        userRepository.save(customer);

        Bill bill = new Bill();
        bill.setBillId("1234123412341234");
        bill.setBillStatus(BillStatus.OPENED);
        bill.setCustomer(customer);
        bill.setStart(LocalDateTime.now());
        bill.setProvider(company);
        bill.setPrice(1000);
        bill.setWarrantyPeriodDays(30);
        bill.setTitle("Phone Purchase");

        billRepository.save(bill);

        Bill closeBill = new Bill();
        closeBill.setBillId("wlkeflsle");
        closeBill.setBillStatus(BillStatus.CLOSED);
        closeBill.setCustomer(customer);
        closeBill.setStart(LocalDateTime.now());
        closeBill.setProvider(company);
        closeBill.setPrice(1000);
        closeBill.setWarrantyPeriodDays(30);
        closeBill.setTitle("Phone Purchase");

        billRepository.save(closeBill);

    }

    @After
    public void tearDown(){
        billRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void getClosedBillsTest() throws Exception {

         String  id = companyRepository.findCompanyByCompanyName("TestCompany").getId().toString();


        mockMvc.perform(get("/get-closed-bills").param("id", id))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    public void getOpenBillsTest() throws Exception {
        String  id = companyRepository.findCompanyByCompanyName("TestCompany").getId().toString();
        mockMvc.perform(get("/get-opened-bills").param("id", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
