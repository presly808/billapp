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
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.BillRepository;
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
    private UserRepository userRepository;
    private Customer customer;

    @Before
    public void setUp() throws Exception {

        this.customer = new Customer();
        Customer customer = this.customer;
        customer.setActivated(true);
        customer.setName("Ivan1");
        customer.setPhone("380932321223");
        customer.setPass("1234");

        userRepository.save(customer);


    }

    @After
    public void tearDown() throws Exception {
        billRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void createBillTest() throws AppException {
        Bill bill = new Bill();
        bill.setBillId("1234123412341234");
        bill.setBillStatus(BillStatus.OPENED);
        bill.setCustomer(customer);
        bill.setStart(LocalDateTime.now());
        bill.setProvider(customer);
        bill.setPrice(1000);
        bill.setWarrantyPeriodDays(30);
        bill.setTitle("Phone Purchase");

        Bill regBill = companyService.createBill(bill);

        Assert.assertThat(regBill, Matchers.notNullValue());
        Assert.assertThat(regBill, Matchers.isA(Bill.class));

    }

}