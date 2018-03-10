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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest extends TestDataHandler {

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @After
    public void tearDown() {
        clearRepositories();
    }

    @Test
    public void shouldReturnOneUser() throws Exception {
        mockMvc.perform(get("/rest/customer")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token)
                .param("name","Ivan"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Ivan"));
    }

    @Test
    public void shouldReturnTwoUsers() throws Exception {
        mockMvc.perform(get("/rest/customers")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void shouldReturnOpenedBills() throws Exception {
        Bill openedBill = testBills.get(0);
        openedBill.setCustomer(testCustomers.get(0));
        openedBill.setBillId("Ivan's bill");
        billRepository.save(openedBill);
        String requestBody = mapper.writeValueAsString(testCustomers.get(0));

        MvcResult result = mockMvc.perform(get("/rest/customer/bills")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();
        assertTrue(body.contains("some_bill"));
        assertNotNull(body);
    }
}
