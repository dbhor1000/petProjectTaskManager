package org.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.util.Assert
import org.springframework.util.ClassUtils
import org.springframework.util.ReflectionUtils
import org.testcontainers.containers.PostgreSQLContainer
import org.example.testcontainers.PostgresqlContainer
import org.springframework.boot.fromApplication

@TestConfiguration
class TestDemoApplication

fun main(args: Array<String>) {
    fromApplication<Main>().with(TestDemoApplication::class.java).run(*args)
}
