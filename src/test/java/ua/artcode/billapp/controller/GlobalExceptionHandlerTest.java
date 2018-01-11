package ua.artcode.billapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void shouldReturnInternalServerError() throws Exception {
        mockMvc.perform(get("/create-bill").contentType(MediaType.APPLICATION_JSON)
                .content("Hello there!"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/get-closed-bills"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
