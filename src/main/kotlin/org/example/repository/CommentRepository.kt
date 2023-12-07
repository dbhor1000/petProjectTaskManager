package org.example.repository

import org.example.model.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, Long> {

    fun getById(id: Long): Comment?
}
