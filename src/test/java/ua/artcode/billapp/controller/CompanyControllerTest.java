package ua.artcode.billapp.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.utils.TestDataHandler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyControllerTest extends TestDataHandler {

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @After
    public void tearDown() {
        clearRepositories();
    }

    @Test
    public void getClosedBillsTest() throws Exception {
        Bill closedBill = testBills.get(1);
        closedBill.setCustomer(testCustomers.get(0));
        closedBill.setProvider(testCompanies.get(0));
        billRepository.save(closedBill);

        String id = companyRepository.findCompanyByCompanyName("TestCompany").getId().toString();


        MvcResult result = mockMvc.perform(get("/rest/get-closed-bills")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token)
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String str = result.getResponse().getContentAsString();
        assertTrue(str.contains("380932321223"));
        assertNotNull(str);
    }

    @Test
    public void getOpenBillsTest() throws Exception {
        Bill openedBill = testBills.get(2);
        openedBill.setCustomer(testCustomers.get(0));
        openedBill.setProvider(testCompanies.get(0));
        billRepository.save(openedBill);

        String id = companyRepository.findCompanyByCompanyName("TestCompany").getId().toString();
        MvcResult result = mockMvc.perform(get("/rest/get-opened-bills")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token)
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String str = result.getResponse().getContentAsString();
        assertTrue(str.contains("380932321223"));
        assertNotNull(str);
    }

    @Test
    public void shouldReturnCreatedBill() throws Exception {
        Bill newBill = testBills.get(0);
        newBill.setStart(null);
        String requestBody = mapper.writeValueAsString(newBill);

        MvcResult result = mockMvc.perform(post("/rest/create-bill")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();
        assertNotNull(body);
        assertTrue(body.contains("some_bill"));
    }
}
