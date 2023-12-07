package org.example

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration

@TestConfiguration
class TestDemoApplication

fun main(args: Array<String>) {
    fromApplication<Main>().with(TestDemoApplication::class.java).run(*args)
}
