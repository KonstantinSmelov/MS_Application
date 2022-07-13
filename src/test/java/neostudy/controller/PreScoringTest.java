package neostudy.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PreScoringTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getApplicationWrongDtoTest() throws Exception {

        String json = "{\n" +
                "  \"amount\": 0,\n" +  //неверный запрос
                "  \"birthdate\": \"2000-06-06\",\n" +
                "  \"email\": \"strin@gmail.ru\",\n" +
                "  \"firstName\": \"sdfg\",\n" +
                "  \"lastName\": \"sdfg\",\n" +
                "  \"middleName\": \"dfgs\",\n" +
                "  \"passportNumber\": \"123565\",\n" +
                "  \"passportSeries\": \"1234\",\n" +
                "  \"term\": 8\n" +
                "}";

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(409));
    }

    @Test
    void getApplicationCorrectDtoTest() throws Exception {

        String json = "{\n" +
                "  \"amount\": 65000,\n" +
                "  \"birthdate\": \"2000-06-06\",\n" +
                "  \"email\": \"string@mail.ru\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"middleName\": \"string\",\n" +
                "  \"passportNumber\": \"123456\",\n" +
                "  \"passportSeries\": \"1234\",\n" +
                "  \"term\": 20\n" +
                "}";

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}