package it.plainvalue.spring.restclient;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestClientController {

	private RestTemplate template;

	@Autowired
	public RestClientController(RestTemplateBuilder builder) {
		template = builder.build();
	}

	@GetMapping("/")
	public String retrieve() {
		Quote quote = template.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		return Objects.toString(quote);
	}
}
