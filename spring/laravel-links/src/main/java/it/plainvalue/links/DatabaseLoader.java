package it.plainvalue.links;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	public static final Link LINK_LARAVELTUTORIAL = new Link("Laravel Tutorial",
			"https://laravel-news.com/your-first-laravel-application",
			"Step by Step Guide to Building Your First Laravel Application");

	@Autowired
	protected LinkRepository links;

	@Override
	public void run(String... args) throws Exception {
		links.save(LINK_LARAVELTUTORIAL);
	}

}
