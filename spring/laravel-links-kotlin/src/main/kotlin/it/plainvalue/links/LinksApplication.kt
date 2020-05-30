package com.example.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(LinksProperties::class)
class LinksApplication

fun main(args: Array<String>) {
	runApplication<LinksApplication>(*args)
}
