package tech.elfin.sendemail.rest;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import tech.elfin.sendemail.model.UserData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserDataControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private UserData userData;

    @Before
    public void setUp() throws Exception {
        userData = new UserData("Кузнецов Андрей Павлович", "4617", "540677",
                "wanderer_2006@mail.ru", "Комментарий");
    }

    /**
     * Интеграционный тест UserDataController -> UserDataService
     * На вход подаются данные клиента
     * @throws Exception
     */
    @Test
    public void sendEmailTest() throws Exception {
        mockMvc.perform(post("/api/sendemail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(userData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("isEmailSended").value(true));
    }
}