package org.example.controller.task

import jakarta.transaction.Transactional
import org.example.controller.dto.common.TaskDto
import org.example.controller.dto.common.TaskDto.Companion.toDto
import org.example.controller.task.dto.CreateTaskRequest
import org.example.controller.task.dto.PatchTaskRequest
import org.example.service.TaskService
import org.springframework.http.HttpStatus
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

    @PostMapping
    fun addTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskDto> {
        val createdTask = taskService.addTask(request.name, request.description, request.userId, request.executeAt).toDto()
        return ResponseEntity.status(HttpStatus.OK).body(createdTask)
    }

    @Transactional
    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable id: Long): ResponseEntity<*> = if (taskService.deleteById(id)) {
        ResponseEntity.status(HttpStatus.OK).body(Unit)
    } else {
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskDto> {
        val taskFound = taskService.getTaskById(id)?.toDto()
        return ResponseEntity(taskFound, HttpStatus.OK)
    }

    @PatchMapping("/{id}")
    fun patchTaskById(@RequestBody request: PatchTaskRequest, @PathVariable id: Long): ResponseEntity<*> {
        val patchedTask = taskService.patchTaskById(request, id)?.toDto()
        return ResponseEntity.status(HttpStatus.OK).body(patchedTask)
    }
}
