package org.example.controller.task

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.transaction.Transactional
import org.example.controller.dto.common.TaskDto
import org.example.controller.dto.common.TaskDto.Companion.toDto
import org.example.controller.task.dto.CreateTaskRequest
import org.example.controller.task.dto.PatchTaskRequest
import org.example.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

    @Operation(summary = "Добавление новой задачи")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешное добавление задачи",
            ),
            ApiResponse(
                responseCode = "400",
                description = "Ошибочный запрос/формат",
            ),
        ],
    )
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun addTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskDto> {
        val createdTask =
            taskService.addTask(request.name, request.description, request.userId, request.executeAt).toDto()
        return ResponseEntity.status(HttpStatus.OK).body(createdTask)
    }

    @Operation(summary = "Удаление задачи")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешное добавление задачи",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Ошибочный запрос/запись отсутствует в базе",
            ),
        ],
    )
    @Transactional
    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable id: Long): ResponseEntity<*> = if (taskService.deleteById(id)) {
        ResponseEntity.status(HttpStatus.OK).body(Unit)
    } else {
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
    }

    @Operation(summary = "Вывод задачи")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешное добавление задачи",
            ),
            ApiResponse(
                responseCode = "500",
                description = "Внутренняя ошибка сервера/запись не найдена в БД", // Код ошибки важен в данном случае?
            ),
        ],
    )
    @GetMapping(
        "/{id}",
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskDto> {
        val taskFound = taskService.getTaskById(id)?.toDto()
        return ResponseEntity(taskFound, HttpStatus.OK)
    }

    @Operation(summary = "Изменить ранее добавленную задачу")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Успешное добавление задачи",
            ),
            ApiResponse(
                responseCode = "500",
                description = "Внутренняя ошибка сервера/запись не найдена в БД", // Код ошибки важен в данном случае?
            ),
        ],
    )
    @PatchMapping(
        "/{id}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun patchTaskById(@RequestBody request: PatchTaskRequest, @PathVariable id: Long): ResponseEntity<TaskDto> {
        val patchedTask = taskService.patchTaskById(request.name, request.description, request.executeAt, id)?.toDto()
        return ResponseEntity.status(HttpStatus.OK).body(patchedTask)
    }

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun getTasks(): ResponseEntity<List<TaskDto>> {
        return ResponseEntity(taskService.findAll().map { it.toDto() }, HttpStatus.OK)
    }
}
