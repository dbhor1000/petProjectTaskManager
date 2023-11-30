package controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.TaskService

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
}