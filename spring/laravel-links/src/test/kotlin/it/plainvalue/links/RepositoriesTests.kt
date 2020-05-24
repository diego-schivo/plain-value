package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
		val entityManager: TestEntityManager,
		val linkRepository: LinkRepository) {

	@Test
	fun `When findByIdOrNull then return Link`() {
		val link = Link("Laravel Tutorial", "https://laravel-news.com/your-first-laravel-application", "Step by Step Guide to Building Your First Laravel Application")
		entityManager.persist(link)
		entityManager.flush()
		val found = linkRepository.findByIdOrNull(link.id!!)
		assertThat(found).isEqualTo(link)
	}
}
