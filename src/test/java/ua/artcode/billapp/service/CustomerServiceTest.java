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
import ua.artcode.billapp.model.*;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CompanyRepository;
import ua.artcode.billapp.repository.CustomerRepository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Customer customer;

    private Company company;

    @Before
    public void setUp() {
        this.customer = new Customer();
        Customer customer = this.customer;
        customer.setActivated(true);
        customer.setName("Ivan");
        customer.setPhone("380932323203");
        customer.setPassword("1234");

        Address address = new Address();
        address.setCity("kiev");
        address.setStreet("ushakova");
        address.setNumber("12A");

        this.company = new Company();
        Company company = this.company;
        company.setActivated(true);
        company.setCompanyName("TestName");
        company.setPhone("380932321293");
        company.setPassword("1234");

        Bill bill = new Bill();
        bill.setBillId("1234123412341234");
        bill.setBillStatus(BillStatus.OPENED);
        bill.setCustomer(customer);
        bill.setStart(OffsetDateTime.now());
        bill.setProvider(company);
        bill.setPrice(1000);
        bill.setWarrantyPeriodDays(30);
        bill.setTitle("Phone Purchase");

        customerRepository.save(customer);
        companyRepository.save(company);
        billRepository.save(bill);



    }

    @After
    public void tearDown() {
        billRepository.deleteAll();
        customerRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void getOpened() throws Exception {
        List<Bill> billList = customerService.getOpenedBills(customer);

        Assert.assertThat(billList, Matchers.notNullValue());
        Assert.assertThat(billList, Matchers.hasSize(1));
    }

}