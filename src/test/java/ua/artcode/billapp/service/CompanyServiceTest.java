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
import java.util.List;

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
    private Company company;

    @Autowired
    private UserRepository userRepository;
    private Customer customer;

    private Address address;

    @Before
    public void setUp() throws Exception {

        this.address = new Address();
        Address address = this.address;
        address.setStreet("test");
        address.setNumber("test");
        address.setCity("test");

        this.customer = new Customer();
        Customer customer = this.customer;
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
    }

    @After
    public void tearDown() throws Exception {
        billRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void getOpened() throws Exception {
        List<Bill> billList = companyService.getOpenedBills(company);

        Assert.assertThat(billList, Matchers.notNullValue());
        Assert.assertThat(billList, Matchers.hasSize(1));
    }

    @Test
    public void createBillTest() throws AppException {
        Bill bill = new Bill();
        bill.setBillId("1234123412341234");
        bill.setBillStatus(BillStatus.OPENED);
        bill.setCustomer(customer);
        bill.setStart(LocalDateTime.now());
        bill.setProvider(company);
        bill.setPrice(1000);
        bill.setWarrantyPeriodDays(30);
        bill.setTitle("Phone Purchase");

        Bill regBill = companyService.createBill(bill);

        Assert.assertThat(regBill, Matchers.notNullValue());
        Assert.assertThat(regBill, Matchers.isA(Bill.class));

    }

}
