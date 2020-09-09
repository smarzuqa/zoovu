package org.zoovu.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String TODAY_LEADERBOARD = "[{\"id\":102,\"username\":\"Nino\",\"score\":200,\"loginDate\":\"2020-09-08\"}," +
            "{\"id\":101,\"username\":\"Klau\",\"score\":100,\"loginDate\":\"2020-09-08\"}," +
            "{\"id\":100,\"username\":\"Sami\",\"score\":10,\"loginDate\":\"2020-09-08\"}]";

    private static final String TODAY = LocalDate.now().toString();

    @Autowired
    private MockMvc mvc;

    @Test
    public void login_ReturnsUserInformation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login")
                .param("username", "Test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"username\":\"Test\",\"score\":0,\"loginDate\":\"" + TODAY + "\"}")));
    }

    @Test
    public void todaysLeaderboard_ReturnsBestUsersOfTheDay() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/leaderboard/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"id\":102,\"username\":\"Nino\",\"score\":200,\"loginDate\":\"" + TODAY + "\"}," +
                                "{\"id\":101,\"username\":\"Klau\",\"score\":100,\"loginDate\":\"" + TODAY + "\"}," +
                                "{\"id\":100,\"username\":\"Sami\",\"score\":10,\"loginDate\":\"" + TODAY + "\"}]")));
    }

    @Test
    public void todaysLeaderboard_ReturnsBestUsersOfTheWeek() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/leaderboard/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"id\":102,\"username\":\"Nino\",\"score\":200,\"loginDate\":\"" + TODAY + "\"}" +
                                ",{\"id\":101,\"username\":\"Klau\",\"score\":100,\"loginDate\":\"" + TODAY + "\"}" +
                                ",{\"id\":100,\"username\":\"Sami\",\"score\":10,\"loginDate\":\"" + TODAY + "\"}]")));
    }

    @Test
    public void todaysLeaderboard_ReturnsBestUsersOfTheYear() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/leaderboard/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"id\":105,\"username\":\"Ma\",\"score\":400,\"loginDate\":\"2020-09-01\"}" +
                                ",{\"id\":103,\"username\":\"Kate\",\"score\":300,\"loginDate\":\"2020-05-01\"}" +
                                ",{\"id\":104,\"username\":\"Tshuli\",\"score\":300,\"loginDate\":\"2020-06-01\"}" +
                                ",{\"id\":102,\"username\":\"Nino\",\"score\":200,\"loginDate\":\"" + TODAY + "\"}" +
                                ",{\"id\":101,\"username\":\"Klau\",\"score\":100,\"loginDate\":\"" + TODAY + "\"}" +
                                ",{\"id\":100,\"username\":\"Sami\",\"score\":10,\"loginDate\":\"" + TODAY + "\"}]")));
    }

    @Test
    public void todaysLeaderboard_ReturnsBestUsersOfAllTime() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/leaderboard/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"id\":105,\"username\":\"Ma\",\"score\":400,\"loginDate\":\"2020-09-01\"}" +
                                ",{\"id\":103,\"username\":\"Kate\",\"score\":300,\"loginDate\":\"2020-05-01\"}" +
                                ",{\"id\":104,\"username\":\"Tshuli\",\"score\":300,\"loginDate\":\"2020-06-01\"}" +
                                ",{\"id\":102,\"username\":\"Nino\",\"score\":200,\"loginDate\":\"" + TODAY + "\"}" +
                                ",{\"id\":101,\"username\":\"Klau\",\"score\":100,\"loginDate\":\"" + TODAY + "\"}" +
                                ",{\"id\":106,\"username\":\"Pa\",\"score\":100,\"loginDate\":\"2019-09-01\"}" +
                                ",{\"id\":107,\"username\":\"Ali\",\"score\":100,\"loginDate\":\"2019-09-01\"}" +
                                ",{\"id\":108,\"username\":\"Kobe\",\"score\":100,\"loginDate\":\"2018-10-01\"}" +
                                ",{\"id\":100,\"username\":\"Sami\",\"score\":10,\"loginDate\":\"" + TODAY + "\"}]")));
    }
}
