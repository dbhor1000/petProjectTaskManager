package org.example.service.impl

import org.example.controller.task.dto.PatchTaskRequest
import org.example.model.Task
import org.example.model.TaskState
import org.example.repository.TaskRepository
import org.example.repository.UserRepository
import org.example.service.TaskService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class TaskServiceImpl(
    private val repository: TaskRepository,
    private val userRepository: UserRepository,
) : TaskService {

    override fun addTask(name: String, description: String, userId: Long, executeAt: OffsetDateTime): Task {
        val user = userRepository.getById(userId) ?: error("user not found by id: '$userId'")
        return repository.save(
            Task(
                name = name,
                description = description,
                createdBy = user,
                executeAt = executeAt,
            ),
        )
    }

    override fun save(task: Task): Task = repository.save(task)

    override fun deleteById(id: Long): Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }
        return false
    }

    override fun getTaskById(id: Long) = repository.findByIdOrNull(id) ?: error("Could not find task by id: '$id'")

    override fun patchTaskById(request: PatchTaskRequest, id: Long): Task? {
        val taskToPatch = repository.getById(id) ?: error("Could not find task by id: '$id'")

        taskToPatch.name = request.name
        taskToPatch.description = request.description
        taskToPatch.taskState = request.taskState
        taskToPatch.executeAt = request.executeAt

        if (request.taskState == TaskState.COMPLETED) {
            taskToPatch.completedAt = OffsetDateTime.now()
        }

        return repository.save(taskToPatch)
    }
}
