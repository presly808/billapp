package ua.artcode.billapp.service;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.artcode.billapp.exception.BillApplicationException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.utils.TestDataHandler;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyServiceTest extends TestDataHandler {

    @Before
    public void setUp() throws IOException {
        initData();
    }

    @After
    public void tearDown() {
        clearRepositories();
    }

    @Test
    public void getOpened() throws Exception {
        Bill closedBill = testBills.get(0);
        closedBill.setProvider(testCompanies.get(0));
        billRepository.save(closedBill);

        List<Bill> billList = companyService.getOpenedBills(testCompanies.get(0));
        assertThat(billList, Matchers.notNullValue());
        assertThat(billList, Matchers.hasSize(1));
    }

    @Test
    public void createBillTest() throws BillApplicationException {
        Bill bill = testBills.get(0);
        Bill regBill = companyService.createBill(bill);

        assertThat(regBill, Matchers.notNullValue());
        assertThat(regBill, Matchers.isA(Bill.class));

    }

    @Test
    public void getClosed() throws BillApplicationException {
        Bill closedBill = testBills.get(1);
        closedBill.setProvider(testCompanies.get(0));
        billRepository.save(closedBill);

        List<Bill> closedBillList = companyService.getClosedBills(testCompanies.get(0));
        assertThat(closedBillList, Matchers.notNullValue());
        assertThat(closedBillList, Matchers.hasSize(1));
    }

    @Test
    public void getCompanyById(){
        Long id = companyRepository.findCompanyByCompanyName("TestCompany").getId();
        Company getterCompany = companyRepository.findOne(id);

        assertThat(getterCompany, Matchers.notNullValue());
        assertEquals(getterCompany, testCompanies.get(0));
    }

}
