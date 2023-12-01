package org.example.controller

import org.example.controller.dto.CreateTaskRequest
import org.example.model.Task
import org.example.service.TaskService
import org.example.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService, private val userService: UserService) {

    @PostMapping
    fun addUser(@RequestBody request: CreateTaskRequest): ResponseEntity<*> {
        val user = userService.getUserById(request.userId) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        val newTask = taskService.save(
            Task(
                name = request.name,
                description = request.description,
                createdBy = user,
            ),
        )
        return ResponseEntity(newTask, HttpStatus.CREATED)
    }
}
