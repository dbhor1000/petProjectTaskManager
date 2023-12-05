package org.example.service.impl

import org.example.controller.dto.*
import org.example.model.Task
import org.example.repository.CommentRepository
import org.example.repository.TaskRepository
import org.example.repository.UserRepository
import org.example.service.TaskService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping

@Service
class TaskServiceImpl(private val repository: TaskRepository, private val userRepository: UserRepository, private val commentRepository: CommentRepository) : TaskService {

    @PostMapping
    override fun addTask(request: CreateTaskRequest): Boolean {
        val user = userRepository.getById(request.userId) ?: return false

        val newTask = repository.save(
            Task(
                name = request.name,
                description = request.description,
                createdBy = user,
            ),
        )
        return true
    }

    override fun save(task: Task): Task = repository.save(task)

    override fun deleteById(id: Long): Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }
        return false
    }

    // override fun getTaskById(id: Long): Task? = repository.findByIdOrNull(id)

    override fun getTaskById(id: Long): GetTaskOutput? {

        val taskFound = repository.findByIdOrNull(id)
        if (taskFound != null) {
            val getTaskOutput = GetTaskOutput(
                id = taskFound.id,
                name = taskFound.name,
                description = taskFound.description,
                createdBy = taskFound.createdBy,
                createdAt = taskFound.createdAt,
                executeAt = taskFound.executeAt,
                appointedBy = taskFound.appointedBy,
                tags = taskFound.tags
            )
            return getTaskOutput
        } else {
            return null
        }
    }

    override fun patchTaskById(request: PatchTaskRequest, id: Long): Boolean {

        val taskToPatch = repository.getById(id) ?: return false // Достали задачу через сервис из репозитория по id в ссылке

        taskToPatch.name = request.name
        taskToPatch.description = request.description
        taskToPatch.executeAt = request.executeAt
        repository.save(taskToPatch)

        return true
    }

    // Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content-Type 'text/plain;charset=UTF-8' is not supported]
    override fun addTagToTaskById(request: AddTagToTask, id: Long): Boolean {

        val taskToPatch = repository.getById(id) ?: return false // Достали задачу через сервис из репозитория по id в ссылке
        val taskToPatchUser = repository.getById(id)?.id ?: return false // Достали id пользователя из задачи
        val user = userRepository.getById(taskToPatchUser) ?: return false // Достали пользователя через сервис из репозитория пользователей по ранее полученному id

        taskToPatch.tags = request.tagsToAdd
        repository.save(taskToPatch)

        return true
    }
}
