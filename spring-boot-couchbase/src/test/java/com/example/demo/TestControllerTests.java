package com.example.demo;

import com.example.demo.config.LocalCouchbaseCluster;
import com.example.demo.controller.TestController;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.hypermedia.HypermediaDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static com.example.demo.ApiDocumentUtils.getDocumentRequest;
import static com.example.demo.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(TestController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class TestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @MockBean
    private LocalCouchbaseCluster localCouchbaseCluster;

    @Test
    public void test() throws Exception {
        List<com.example.demo.model.Test> list = new ArrayList<>();
        com.example.demo.model.Test data = new com.example.demo.model.Test();
        data.setId("id");
        data.setField("field");
        data.setValue("value");
        list.add(data);

        given(testService.getTest(any()))
                .willReturn(list);

        ResultActions result = this.mockMvc.perform(
                get("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(document("test",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.STRING).description("id"),
                                fieldWithPath("[].value").type(JsonFieldType.STRING).description("value"),
                                fieldWithPath("[].field").type(JsonFieldType.STRING).description("field")
                        )
                ));

    }
}
