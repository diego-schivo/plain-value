package com.example.blog

import org.springframework.data.repository.CrudRepository

interface LinkRepository : CrudRepository<Link, Long> {
	fun findAllByOrderByAddedAtDesc(): Iterable<Link>
}
