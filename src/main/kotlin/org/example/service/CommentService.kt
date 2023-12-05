package org.example.service

import org.example.model.Comment
import org.example.model.Task

interface CommentService {

    fun save(comment: Comment): Comment
    fun deleteById(id: Long): Boolean
    fun getCommentById(id: Long): Comment?

}
