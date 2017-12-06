package ua.artcode.billapp.controller;


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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    private static final String MOCK_JSON_PATH = "/mockBillJSON.json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;

    @Before
    public void tearDown() throws Exception{
        userRepository.deleteAll();
        billRepository.deleteAll();
    }

    @Test
    public void shouldReturnCreatedBill() throws Exception {
        String path = new File(CompanyControllerTest.class
                .getResource(MOCK_JSON_PATH)
                .getFile())
                .getAbsolutePath();
        File json = new File(path);
        StringBuffer buff = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(json)));
        String temp;
        while ((temp = reader.readLine()) != null){
            buff.append(temp + "\n");
        }

        mockMvc.perform(post("/create-bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buff.toString()))
                .andExpect(status().isOk());
    }

}
