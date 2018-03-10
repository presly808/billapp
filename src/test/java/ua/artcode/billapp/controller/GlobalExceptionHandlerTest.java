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
import ua.artcode.billapp.utils.TestDataHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest extends TestDataHandler {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @After
    public void tearDown() {
        clearRepositories();
    }


    @Test
    public void shouldReturnInternalServerError() throws Exception {
        mockMvc.perform(get("/rest/create-bill")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("Hello there!"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/get-closed-bills")
                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void shouldReturnJwtIsMissing() throws Exception{
//        mockMvc.perform(get("/rest/get-closed-bills"))
//                .andDo(print())
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    public void shouldReturnJwtIsINcorrect() throws Exception{
//        mockMvc.perform(get("/rest/customers")
//                .header(AUTH_KEY, AUTH_VALUE_TOKEN + token + "1"))
//                .andDo(print())
//                .andExpect(status().isInternalServerError());
//    }
}
