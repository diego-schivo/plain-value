package com.example.blog

import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(private val repository: LinkRepository,
					 private val properties: LinksProperties) {

	@GetMapping("/")
	fun links(model: Model): String {
		model["title"] = properties.title
		model["links"] = repository.findAllByOrderByAddedAtDesc()
		return "links"
	}

}
