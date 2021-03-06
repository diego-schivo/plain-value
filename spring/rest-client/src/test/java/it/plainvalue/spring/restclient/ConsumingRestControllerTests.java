package it.plainvalue.spring.restclient;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumingRestControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void shouldReturnQuoteMessage() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				content().string(matchesRegex("Quote\\[type='success', value=Value\\[id=\\d+, quote='.*?'\\]\\]")));
	}
}