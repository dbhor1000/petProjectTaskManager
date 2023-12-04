package org.example.controller

import jakarta.transaction.Transactional
import org.example.controller.dto.CreateTaskRequest
import org.example.controller.dto.PatchTaskRequest
import org.example.model.Task
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
        val user =
            userService.getUserById(request.userId) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        val newTask = taskService.save(
            Task(
                name = request.name,
                description = request.description,
                createdBy = user,
            ),
        )
        return ResponseEntity(newTask, HttpStatus.CREATED)
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

        return if (!taskFound.isEmpty) {
            return ResponseEntity(taskFound, HttpStatus.OK)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    // @PatchMapping("/{id}")
    // fun patchTaskById(@PathVariable id: Long): ResponseEntity<*> {
    //
    //    val taskFound = taskService.getTaskById(id)
    //
    //    return if (!taskFound.isEmpty) {
    //        return ResponseEntity(taskFound, HttpStatus.OK)
    //    } else {
    //        ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
    //    }
    // }

    // Можно сильно оптимизровать?
    @PatchMapping("/{id}")
    fun patchTaskById(@RequestBody request: PatchTaskRequest, @PathVariable id: Long): ResponseEntity<*> {

        // val user =
        //    userService.getUserById(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)

        val taskToPatchUser = taskService.getTaskById(id).id ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)

        val newTask = taskService.save(
            Task(
                createdBy = taskToPatchUser, // /
                name = request.name,
                description = request.description,
                executeAt = request.executeAt,
            ),
        )
        return ResponseEntity(newTask, HttpStatus.CREATED)
    }
}
