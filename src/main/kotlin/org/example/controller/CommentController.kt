package org.example.controller

import jakarta.transaction.Transactional
import org.example.controller.dto.CreateCommentRequest
import org.example.controller.dto.PatchCommentRequest
import org.example.controller.dto.common.CommentDto
import org.example.controller.dto.common.CommentDto.Companion.toDto
import org.example.controller.dto.common.TaskDto.Companion.toDto
import org.example.service.CommentService
import org.example.service.TaskService
import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(@Autowired private val commentService: CommentService, private val taskService: TaskService, private val userService: UserService) {

    // 404 через postman, не устанавливается контакт с контроллером
    // @PostMapping
    // fun addComment(@RequestBody request: CreateCommentRequest): ResponseEntity<CommentDto> {
    //    return if (commentService.addComment(request)) {
    //        ResponseEntity.status(HttpStatus.CREATED).body(Unit)
    //    } else {
    //        ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
    //    }
    // }

    @PostMapping
    fun addComment(@RequestBody request: CreateCommentRequest): ResponseEntity<CommentDto> {
        val createdComment = commentService.addComment(request.commentText, request.author, request.correspondingTask).toDto()
        return ResponseEntity.status(HttpStatus.OK).body(createdComment)
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
        return if (commentService.patchCommentById(request, id)) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        } else {
            ResponseEntity.status(HttpStatus.ACCEPTED).body(Unit)
        }
    }
}
