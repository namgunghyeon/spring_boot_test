package com.example.demo;

import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    private final Logger logger = LoggerFactory.getLogger(ControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public UserResponseDTO loginAndMe(String id, String pw) throws Exception {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername(id);
        userLoginDTO.setPassword(pw);

        ResultActions resultActions = this.mockMvc.perform(post("/users/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLoginDTO)));

        String response = resultActions.andReturn().getResponse().getContentAsString();
        TokenResponseDTO tokenResponseDTO = objectMapper.readValue(response, TokenResponseDTO.class);

        ResultActions resultActionsMe = this.mockMvc.perform(get("/users/me")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + tokenResponseDTO.getToken()));

        String stringMe = resultActionsMe.andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(stringMe, UserResponseDTO.class);
    }

    class TestResponse {
        private UserResponseDTO userResponseDTO;
        private String requestKey;

        public TestResponse(UserResponseDTO userResponseDTO, String requestKey) {
            this.userResponseDTO = userResponseDTO;
            this.requestKey = requestKey;
        }

        public UserResponseDTO getUserResponseDTO() {
            return userResponseDTO;
        }

        public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
            this.userResponseDTO = userResponseDTO;
        }

        public String getRequestKey() {
            return requestKey;
        }

        public void setRequestKey(String requestKey) {
            this.requestKey = requestKey;
        }
    }

    @Test
    public void testMe() throws Exception {
        List<UserResponseDTO> ret = new ArrayList<>();
        List<String> keyList = Arrays.asList(
                "admin5", "admin6", "admin6", "admin5", "admin6",
                "admin5", "admin6", "admin6", "admin5", "admin6",
                "admin5", "admin6", "admin6", "admin5", "admin6"
        );
        ExecutorService executor = Executors.newFixedThreadPool(15);
        List<Future<TestResponse>> futureList = new ArrayList<>();

        keyList.forEach(key -> {
            futureList.add(executor.submit(() -> new TestResponse(loginAndMe(key,key), key)));
        });

        futureList.forEach(f -> {
            try {
                Assertions.assertEquals(f.get().getUserResponseDTO().getEmail(), f.get().getRequestKey()+"@email.com");
            } catch (Exception e) { }
        });
    }
}
