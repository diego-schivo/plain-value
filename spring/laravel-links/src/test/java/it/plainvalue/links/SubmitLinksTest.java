package it.plainvalue.links;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HtmlController.class)
public class SubmitLinksTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected LinkRepository links;

	@Test
	public void guestCanSubmitANewLink() throws Exception {
		String title = "Example title";
		String url = "http://example.com";
		String description = "Example description";
		mockMvc.perform(post("/submit").param("title", title).param("url", url).param("description", description))
				.andExpect(model().hasNoErrors());
		verify(links, times(1)).save(eq(new Link(title, url, description)));
	}

	@Test
	public void linkIsNotCreatedIfValidationFails() throws Exception {
		mockMvc.perform(post("/submit"))
				.andExpect(model().attributeHasFieldErrors("link", "title", "url", "description"));
	}

	@Test
	public void linkIsNotCreatedWithAnInvalidUrl() throws Exception {
		String title = "Example title";
		String description = "Example description";
		for (String url : new String[] { "//invalid-url.com", "/invalid-url", "foo.com" }) {
			mockMvc.perform(post("/submit").param("title", title).param("url", url).param("description", description))
					.andExpect(model().attributeHasFieldErrorCode("link", "url", "URL"));
		}
	}

	@Test
	public void maxLengthFailsWhenTooLong() throws Exception {
		String title = "a".repeat(256);
		String url = "http://" + "a".repeat(256 - "http://".length());
		String description = "a".repeat(256);
		mockMvc.perform(post("/submit").param("title", title).param("url", url).param("description", description))
				.andExpect(model().attributeHasFieldErrorCode("link", "title", "Size"))
				.andExpect(model().attributeHasFieldErrorCode("link", "url", "Size"))
				.andExpect(model().attributeHasFieldErrorCode("link", "description", "Size"));
	}

	@Test
	public void maxLengthSucceedsWhenUnderMax() throws Exception {
		String title = "a".repeat(255);
		String url = "http://" + "a".repeat(255 - "http://".length());
		String description = "a".repeat(255);
		mockMvc.perform(post("/submit").param("title", title).param("url", url).param("description", description));
		verify(links, times(1)).save(eq(new Link(title, url, description)));
	}

}
