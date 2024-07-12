package api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.unigap.DemoApplication;
import vn.unigap.api.dto.in.CreateEmployerDtoIn;
import vn.unigap.api.dto.out.CustomResponse;
import vn.unigap.api.service.EmployerService;

@SpringBootTest(classes = DemoApplication.class)
@Slf4j
@AutoConfigureMockMvc
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;

    // declare
    private CreateEmployerDtoIn requestData;
    private CustomResponse<Object> customResponse;

    // init data api input and output
    @BeforeEach
    void initData() {
        requestData = CreateEmployerDtoIn.builder()
                .email("unitTest@gmail.com")
                .name("Unit test")
                .description("unit test description")
                .provinceId(10)
                .build();

        customResponse = CustomResponse.builder()
                .errorCode(0)
                .statusCode(201)
                .message("Create employer successfully")
                .object("")
                .build();
    }

    @Test
    void createEmployer_validRequest_success() throws Exception {
        // given
        // convert data from java object to json string
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(requestData);

        // mock to employerService.createEmployer
        Mockito.when(employerService.createEmployer(ArgumentMatchers.any()))
                .thenReturn(String.valueOf(customResponse));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value(0));

        // then
    }

    @Test
    void createEmployer_usernameInvalid_fail() throws Exception {
        // given
        requestData.setName("   ");
        // convert data from java object to json string
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(requestData);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Please provide name"));

        // then
    }
}
