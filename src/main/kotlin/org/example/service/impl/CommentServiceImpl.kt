package org.example.service.impl

import org.example.model.Comment
import org.example.model.Task
import org.example.repository.CommentRepository
import org.example.service.CommentService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(private val repository: CommentRepository) : CommentService {


    override fun save(comment: Comment): Comment = repository.save(comment)

    override fun deleteById(id: Long): Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }
        return false
    }

    override fun getCommentById(id: Long): Comment? = repository.findByIdOrNull(id)
}