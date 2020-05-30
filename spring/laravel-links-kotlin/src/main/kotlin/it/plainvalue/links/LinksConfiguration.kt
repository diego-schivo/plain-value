package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LinksConfiguration {

    @Bean
    fun databaseInitializer(linkRepository: LinkRepository) = ApplicationRunner {

        linkRepository.save(Link(
				title = "Laravel Tutorial",
				url = "https://laravel-news.com/your-first-laravel-application",
				description = "Step by Step Guide to Building Your First Laravel Application"
		))
    }
}
