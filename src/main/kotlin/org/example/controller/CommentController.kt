package org.example.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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

    //---> Тестируем
    @Operation(summary = "Добавить комментарий")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешное добавление комментария"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Ошибочный запрос" //Код ошибки важен в данном случае?
            )
        ]
    )
    @PostMapping
    fun addComment(@RequestBody request: CreateCommentRequest): ResponseEntity<CommentDto> {
        val createdComment = commentService.addComment(request.commentText, request.author, request.correspondingTask)?.toDto()
        return ResponseEntity.status(HttpStatus.OK).body(createdComment)
    }

    @Operation(summary = "Удалить комментарий")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Успешное добавление пользователя"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Ошибочный запрос/отсутствует запись в БД" //Код ошибки важен в данном случае?
            )
        ]
    )
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

    @Operation(summary = "Вывод комментария")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешный вывод комментария"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Ошибочный запрос/внутренняя ошибка сервера" //Код ошибки важен в данном случае?
            )
        ]
    )
    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<CommentDto>? {
        val commentFound = commentService.getCommentById(id)?.toDto()
        return ResponseEntity(commentFound, HttpStatus.OK)
    }

    @Operation(summary = "Вывод комментария")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешный вывод комментария"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Ошибочный запрос/внутренняя ошибка сервера" //Код ошибки важен в данном случае?
            )
        ]
    )
    @PatchMapping("/{id}")
    fun patchCommentById(@RequestBody request: PatchCommentRequest, @PathVariable id: Long): ResponseEntity<*> {

        val patchedComment = commentService.patchCommentById(request.commentText, id)?.toDto()
        return ResponseEntity(patchedComment, HttpStatus.OK)
    }
}
