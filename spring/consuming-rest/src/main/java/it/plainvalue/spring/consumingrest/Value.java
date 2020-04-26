package it.plainvalue.spring.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

	private Long id;
	private String quote;

	public Long getId() {
		return id;
	}

	public String getQuote() {
		return quote;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "{id: " + id + ", quote: '" + quote + "'}";
	}
}