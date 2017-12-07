package ua.artcode.billapp.service;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.*;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import ua.artcode.billapp.repository.UserRepository;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;
    private Customer customer;
    private Company provider;

    @Before
    public void setUp() throws Exception {

        this.customer = new Customer();
        Customer customer = this.customer;
        customer.setActivated(true);
        customer.setName("Ivan1");
        customer.setPhone("380932321223");
        customer.setPass("1234");

        userRepository.save(customer);

        Address address = new Address();
        address.setCity("kiev");
        address.setStreet("ushakova");
        address.setNumber("12A");

        this.provider = new Company();
        Company provider = this.provider;
        provider.setType("Market");
        provider.setAdditionalInfo("some info");
        provider.setAddress(address);
        provider.setCompanyName("DDS");
        provider.setPass("123");
        provider.setPhone("380966967325");

        companyRepository.save(provider);




    }

    @After
    public void tearDown() throws Exception {
        billRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void createBillTest() throws AppException {
        Bill bill = new Bill();
        bill.setBillId("1234123412341234");
        bill.setBillStatus(BillStatus.OPENED);
        bill.setCustomer(customer);
        bill.setStart(LocalDateTime.now());
        bill.setProvider(provider);
        bill.setPrice(1000);
        bill.setWarrantyPeriodDays(30);
        bill.setTitle("Phone Purchase");

        Bill regBill = companyService.createBill(bill);

        Assert.assertThat(regBill, Matchers.notNullValue());
        Assert.assertThat(regBill, Matchers.isA(Bill.class));

    }

}