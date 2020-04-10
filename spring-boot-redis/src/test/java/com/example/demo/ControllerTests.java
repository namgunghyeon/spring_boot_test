package com.example.demo;

import com.example.demo.config.EmbeddedRedisConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void start () {
        EmbeddedRedisConfig.start();
    }

    @AfterAll
    static void stop () {
        EmbeddedRedisConfig.stop();
    }

    @Test
    void getTest() throws Exception {
        ResultActions result = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/redis")
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andReturn().getResponse().toString();
    }
}
