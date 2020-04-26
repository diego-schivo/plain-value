package it.plainvalue.spring.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumingRestController {

	private RestTemplate restTemplate;

	@Autowired
	public ConsumingRestController(RestTemplateBuilder builder) {
		restTemplate = builder.build();
	}

	@GetMapping("/")
	public String consume() {
		Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		return String.format("quote=%s", quote);
	}
}
