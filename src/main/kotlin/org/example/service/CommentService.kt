package org.example.service

import org.example.model.Comment

interface CommentService {

    fun save(comment: Comment): Comment
    fun deleteById(id: Long): Boolean
    fun getCommentById(id: Long): Comment?
}
