package tds.company.api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void cadastrarUrl() throws Exception {
		String longUrl = "https://example.com/very/long/url";
		String requestBody = "{\"longUrl\":\"" + longUrl + "\"}";

		mockMvc.perform(post("/shorten-url")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.shortUrl", startsWith("http://localhost/")));
	}
	@Test
	public void verificarDirecionamento() throws Exception {
		String shortUrlId = "0rDtF";
		String longUrl = "https://example.com/very/long/url";
		mockMvc.perform(MockMvcRequestBuilders.get("/" + shortUrlId))
				.andExpect(status().isFound())
				.andExpect(header().string("Location", longUrl));
	}

	@Test
	public void verificarTotalAcessosMedia() throws Exception {
		String shortUrlId = "0rDtF";
		mockMvc.perform(MockMvcRequestBuilders.get("/stats/" + shortUrlId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalAccesses", is(1)))
				.andExpect(jsonPath("$.averageAccessesPerDay", is(1.0)));
	}

	@Test
	public void verificarId() throws Exception {
		String nonExistentShortUrlId = "nonExistent";
		mockMvc.perform(MockMvcRequestBuilders.get("/" + nonExistentShortUrlId))
				.andExpect(status().isNotFound());
	}




}
