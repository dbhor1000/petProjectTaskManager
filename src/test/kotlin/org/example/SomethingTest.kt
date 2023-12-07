package org.example

import org.example.repository.CommentRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

class SomethingTest : AbstractIntegrationTest() {

   @Autowired
   lateinit var commentRepository: CommentRepository

   
    @Test
    internal fun happyWay() {
        assertEquals(0, commentRepository.count())
        assert(true)
    }
}
