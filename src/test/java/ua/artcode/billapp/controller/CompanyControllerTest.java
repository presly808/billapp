package ua.artcode.billapp.controller;

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
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.UserRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;
    private Bill bill;
    private String json;

    @Before
    public void setUp(){
        Gson gson = new Gson();

        this.bill = new Bill();
        Bill bill = this.bill;
        bill.setBillId("bill");
        bill.setWarrantyPeriodDays(10);
        bill.setTitle("new title");
        bill.setPrice(100);

        json = gson.toJson(bill);
    }

    @After
    public void tearDown() throws Exception{
        userRepository.deleteAll();
        billRepository.deleteAll();
    }

    @Test
    public void shouldReturnCreatedBill() throws Exception {
        mockMvc.perform(post("/create-bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

}
