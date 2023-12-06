package org.example.service.impl

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

    override fun getCommentById(id: Long): Comment? {

        val commentFound = repository.findByIdOrNull(id) ?: error("user not found by id: '$id'")
        return commentFound
    }

    // override fun addComment(request: CreateCommentRequest): Comment? {
    //
    //    val taskCommented = taskRepository.getById(request.correspondingTask) ?: error("user not found by id: '$userId'")
    //    val authorCommented = userRepository.getById(request.author) ?: error("user not found by id: '$userId'")
    //
    //    val newComment = repository.save(
    //        Comment(
    //            commentText = request.commentText,
    //            author = authorCommented,
    //            correspondingTask = taskCommented,
    //        ),
    //    )
    //    return true
    // }

    override fun addComment(commentText: String, author: Long, correspondingTask: Long,): Comment {

        val taskCommented = taskRepository.getById(correspondingTask) ?: error("user not found by id: '$correspondingTask'")
        val authorCommented = userRepository.getById(author) ?: error("user not found by id: '$author'")

        val newComment = repository.save(
            Comment(
                commentText = commentText,
                author = authorCommented,
                correspondingTask = taskCommented,
            ),
        )
        return newComment
    }

    override fun patchCommentById(commentText: String, id: Long): Comment? {

        val commentToPatch = repository.getById(id) ?: error("user not found by id: '$id'")

        commentToPatch.commentText = commentText
        repository.save(commentToPatch)

        return commentToPatch
    }
}
