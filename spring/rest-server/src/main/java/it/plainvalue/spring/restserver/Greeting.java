package it.plainvalue.spring.restserver;

public class Greeting {

	private String content;

	public Greeting(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return String.format("Greeting[content='%s']", content);
	}
}