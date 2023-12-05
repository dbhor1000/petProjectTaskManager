package org.example.controller

import jakarta.transaction.Transactional
import org.example.controller.dto.AddTagToTask
import org.example.controller.dto.CreateTaskRequest
import org.example.controller.dto.PatchTaskRequest
import org.example.service.TaskService
import org.example.service.UserService
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
class TaskController(private val taskService: TaskService, private val userService: UserService) {

    @PostMapping
    fun addTask(@RequestBody request: CreateTaskRequest): ResponseEntity<*> {

        if (taskService.addTask(request) == true) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Unit)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable id: Long): ResponseEntity<*> {
        val deleteSuccessful = taskService.deleteById(id)

        return if (deleteSuccessful) {
            ResponseEntity.status(HttpStatus.OK).body(Unit)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<*> {

        val taskFound = taskService.getTaskById(id)

        return if (taskFound != null) {
            return ResponseEntity(taskFound, HttpStatus.OK)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    @PatchMapping("/{id}")
    fun patchTaskById(@RequestBody request: PatchTaskRequest, @PathVariable id: Long): ResponseEntity<*> {

        if (taskService.patchTaskById(request, id) == true) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Unit)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    // Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content-Type 'text/plain;charset=UTF-8' is not supported]
    @PatchMapping("/addTag/{id}")
    fun addTagToTaskById(@RequestBody request: AddTagToTask, @PathVariable id: Long): ResponseEntity<*> {

        if (taskService.addTagToTaskById(request, id) == true) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Unit)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }
}
