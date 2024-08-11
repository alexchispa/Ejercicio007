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
    @WithMockUser(roles = "USER")
    public void postBookWithInvalidData_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\",\"author\":\"\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void getBookWithoutAuth_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/books/1"))
            .andExpect(status().isUnauthorized());
    }
}