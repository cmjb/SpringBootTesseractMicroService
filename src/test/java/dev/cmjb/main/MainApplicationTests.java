package dev.cmjb.main;

import dev.cmjb.main.services.UploadedFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.NestedTestConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.context.NestedTestConfiguration.EnclosingConfiguration.OVERRIDE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
@NestedTestConfiguration(OVERRIDE)
class MainApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UploadedFileService storageService;

	@Test
	public void shouldSaveUploadedFile() throws Exception {
		File file = new File("src/test/resources/dev/cmjb/main/testimage2.jpg");

		FileInputStream input = new FileInputStream(file);
		MockMultipartFile multipartFile = new MockMultipartFile("file",
				file.getName(), "image/jpeg", input);

		this.mvc.perform(multipart("/").file(multipartFile))
				.andExpect(status().isOk())
				.andExpect(content().string("THIS IMAGE\nHAS TEXT IN IT\n"));

		then(this.storageService).should().store(multipartFile);
	}

	@Test
	public void contextLoads() {
		assertThat(storageService).isNotNull();
	}
}
