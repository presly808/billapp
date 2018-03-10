package ua.artcode.billapp.service;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.utils.TestDataHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest extends TestDataHandler {

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @After
    public void tearDown() {
        clearRepositories();
    }

    @Test
    public void getOpened() throws Exception {
        Bill testBill = testBills.get(0);
        testBill.setProvider(testCompanies.get(0));
        testBill.setCustomer(testCustomers.get(0));
        billRepository.save(testBill);

        List<Bill> billList = customerService.getOpenedBills(testCustomers.get(0));
        Assert.assertThat(billList, Matchers.notNullValue());
        Assert.assertThat(billList, Matchers.hasSize(1));
    }

}