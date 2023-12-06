package org.example.service.impl

import org.example.controller.dto.CreateCommentRequest
import org.example.controller.dto.GetCommentResponse
import org.example.controller.dto.PatchCommentRequest
import org.example.model.Comment
import org.example.repository.CommentRepository
import org.example.repository.TaskRepository
import org.example.repository.UserRepository
import org.example.service.CommentService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(private val repository: CommentRepository, private val taskRepository: TaskRepository, private val userRepository: UserRepository) : CommentService {

    override fun save(comment: Comment): Comment = repository.save(comment)

    override fun deleteById(id: Long): Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }
        return false
    }

    override fun getCommentById(id: Long): GetCommentResponse? {

        val commentFound = repository.findByIdOrNull(id)
        if (commentFound != null) {
            val getCommentResponse = GetCommentResponse(
                commentText = commentFound.commentText,
                author = commentFound.author,
                correspondingTask = commentFound.correspondingTask

            )
            return getCommentResponse
        } else {
            return null
        }
    }

    override fun addComment(request: CreateCommentRequest): Boolean {

        val taskCommented = taskRepository.getById(request.correspondingTask) ?: return false
        val authorCommented = userRepository.getById(request.author) ?: return false

        val newComment = repository.save(
            Comment(
                commentText = request.commentText,
                author = authorCommented,
                correspondingTask = taskCommented,
            ),
        )
        return true
    }

    override fun patchCommentById(request: PatchCommentRequest, id: Long): Boolean {

        val commentToPatch = repository.getById(id) ?: return false // Достали задачу через сервис из репозитория по id в ссылке

        commentToPatch.commentText = request.commentText
        repository.save(commentToPatch)

        return true
    }
}
