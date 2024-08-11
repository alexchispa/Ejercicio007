package es.cic.curso.ejercicio007;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;


   @Test
@WithMockUser
public void postBookWithInvalidData_ShouldReturnBadRequest() throws Exception {
    mockMvc.perform(post("/Books")
            .contentType(MediaType.APPLICATION_JSON)
            ) 
            .andExpect(status().isBadRequest());
}

    @Test
    public void getBookWithoutAuth_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/books/1"))
            .andExpect(status().is4xxClientError());
    }
}