package org.example.controller

import jakarta.transaction.Transactional
import org.example.controller.dto.*
import org.example.model.Comment
import org.example.model.Task
import org.example.service.CommentService
import org.example.service.TaskService
import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(@Autowired private val commentService: CommentService, @Autowired private val taskService: TaskService, @Autowired private val  userService: UserService) {

    //Postman не видит endpoint. Пару дней назад, если не ошибаюсь, решали похожую проблему. Применил всё, что вспомнил, но решить проблему не получилось. Методы непроверенные, соответственно.
    @PostMapping
    fun addComment(@RequestBody request: CreateCommentRequest): ResponseEntity<*> {

        val taskCommented = taskService.getTaskById(request.correspondingTask) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        val authorCommented = userService.getUserById(request.author) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)

        val newComment = commentService.save(
            Comment(
                commentText = request.commentText,
                author = authorCommented,
                correspondingTask = taskCommented,
            ),
        )
        return ResponseEntity(newComment, HttpStatus.CREATED)
    }

    @Transactional
    @DeleteMapping("/{id}")
    fun deleteCommentById(@PathVariable id: Long): ResponseEntity<*> {
        val deleteSuccessful = commentService.deleteById(id)

        return if (deleteSuccessful) {
            ResponseEntity.status(HttpStatus.OK).body(Unit)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }


    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<*> {

        val commentFound = commentService.getCommentById(id)

        return if (commentFound != null) {
            return ResponseEntity(commentFound, HttpStatus.OK)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    @PatchMapping("/{id}")
    fun patchCommentById(@RequestBody request: PatchCommentRequest, @PathVariable id: Long): ResponseEntity<*> {

        val commentToPatch = commentService.getCommentById(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали задачу через сервис из репозитория по id в ссылке

        commentToPatch.commentText = request.commentText
        commentService.save(commentToPatch)

        return ResponseEntity(commentToPatch, HttpStatus.CREATED)
    }

}
