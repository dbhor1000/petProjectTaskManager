package org.example.controller

import jakarta.transaction.Transactional
import org.example.controller.dto.AddTagToTask
import org.example.controller.dto.CreateTaskRequest
import org.example.controller.dto.PatchTaskRequest
import org.example.model.Task
import org.example.service.TaskService
import org.example.service.UserService
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
import org.springframework.web.bind.annotation.RequestMethod
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

        return if (taskFound != null) {
            return ResponseEntity(taskFound, HttpStatus.OK)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)
        }
    }

    @PatchMapping("/{id}")
    fun patchTaskById(@RequestBody request: PatchTaskRequest, @PathVariable id: Long): ResponseEntity<*> {

        // val user =
        //    userService.getUserById(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)

        val taskToPatch = taskService.getTaskById(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали задачу через сервис из репозитория по id в ссылке
        //val taskToPatchUser = taskService.getTaskById(id)?.id ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали id пользователя из задачи
        //val user = userService.getUserById(taskToPatchUser) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали пользователя через сервис из репозитория пользователей по ранее полученному id

        taskToPatch.name = request.name
        taskToPatch.description = request.description
        taskToPatch.executeAt = request.executeAt
        taskService.save(taskToPatch)

        //val newTask = taskService.save(
        //    Task(
        //        id = taskToPatch.id,
        //        createdBy = user,
        //        name = request.name,
        //        description = request.description,
        //        executeAt = request.executeAt,
        //    ),
        //)
        return ResponseEntity(taskToPatch, HttpStatus.CREATED)
    }

    //Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content-Type 'text/plain;charset=UTF-8' is not supported]
    @PatchMapping("/addTag/{id}")
    fun addTagToTaskById(@RequestBody request: AddTagToTask, @PathVariable id: Long): ResponseEntity<*> {

        // val user =
        //    userService.getUserById(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit)

        val taskToPatch = taskService.getTaskById(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали задачу через сервис из репозитория по id в ссылке
        val taskToPatchUser = taskService.getTaskById(id)?.id ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали id пользователя из задачи
        val user = userService.getUserById(taskToPatchUser) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Unit) //Достали пользователя через сервис из репозитория пользователей по ранее полученному id

        taskToPatch.tags = request.tagsToAdd
        taskService.save(taskToPatch)

        return ResponseEntity(taskToPatch, HttpStatus.CREATED)
    }



}
