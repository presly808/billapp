package ua.artcode.billapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.CustomerRepository;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() throws IOException {
        //Elder black magic. Don't touch this!
        List<Customer> customers = mapper.readValue(this.getClass().getResourceAsStream("/customers.json"), new TypeReference<List<Customer>>(){});
        customerRepository.save(customers);
    }

    @After
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldReturnOneUser() throws Exception {
        mockMvc.perform(get("/customer").param("name","Ivan"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Ivan"));

    }

    @Test
    public void shouldReturnTwoUsers() throws Exception {
        assertNotNull("Customer repository not found!", customerRepository);
        mockMvc.perform(get("/customers"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}
