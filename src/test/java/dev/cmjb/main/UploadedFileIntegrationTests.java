package dev.cmjb.main;

import dev.cmjb.main.services.UploadedFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadedFileIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UploadedFileService storageService;

    @Test
    public void shouldUploadFile() {
        ClassPathResource resource = new ClassPathResource("testimage2.jpg", getClass());

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", resource);
        ResponseEntity<String> response = this.restTemplate.postForEntity("/", map,
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(storageService).should().store(any(MultipartFile.class));
    }
}
