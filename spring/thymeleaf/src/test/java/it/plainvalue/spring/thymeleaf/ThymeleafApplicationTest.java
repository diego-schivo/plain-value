package it.plainvalue.spring.thymeleaf;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HelloController.class)
public class ThymeleafApplicationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void index() throws Exception {
    mockMvc.perform(get("/index.html")).andExpect(content().string(containsString("Name:")));
  }

  @Test
  public void helloWorld() throws Exception {
    mockMvc.perform(get("/hello")).andExpect(content().string(containsString("Hello, World!")));
  }

  @Test
  public void helloFrodo() throws Exception {
    mockMvc
        .perform(get("/hello").param("name", "Frodo"))
        .andExpect(content().string(containsString("Hello, Frodo!")));
  }
}
