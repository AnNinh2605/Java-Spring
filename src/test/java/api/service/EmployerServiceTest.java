package api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import vn.unigap.DemoApplication;
import vn.unigap.api.dto.in.CreateEmployerDtoIn;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.service.EmployerService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class EmployerServiceTest {

    @Autowired
    private EmployerService employerService;

    @MockBean
    private EmployerRepository employerRepository;

    // declare
    private CreateEmployerDtoIn requestData;
    private Employer response;

    // init data api input and output
    @BeforeEach
    void initData() {
        requestData = CreateEmployerDtoIn.builder()
                .email("unitTest@gmail.com")
                .name("Unit test")
                .description("unit test description")
                .provinceId(10)
                .build();

        response = Employer.builder()
                .email("unitTest@gmail.com")
                .name("Unit test")
                .description("unit test description")
                .province(10)
                .build();
    }

    @Test
    void createEmployer_validRequest_success() {
        // given
        // mock to employerRepository
        when(employerRepository.existsByEmail(anyString())).thenReturn(false);
        when(employerRepository.save(any())).thenReturn(response);

        // when
        var user = employerService.createEmployer(requestData);

        // then
        Assertions.assertEquals(user,"Create employer successful");
    }

    @Test
    void createEmployer_emailExist_fail() {
        // given
        // mock to employerRepository
        when(employerRepository.existsByEmail(anyString())).thenReturn(true);

        // when
        var exception = Assertions.assertThrows(DuplicateKeyException.class,
                () -> employerService.createEmployer(requestData));

        // then
        Assertions.assertEquals(exception.getMessage(), "Email already exists: " + response.getEmail());
    }
}
