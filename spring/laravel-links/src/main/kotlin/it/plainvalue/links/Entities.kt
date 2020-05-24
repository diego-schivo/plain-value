package com.example.blog

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Link(
		var title: String,
		var url: String,
		var description: String,
		var addedAt: LocalDateTime = LocalDateTime.now(),
		@Id @GeneratedValue var id: Long? = null)
