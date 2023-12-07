package org.example.service.impl

import org.example.model.Task
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

    override fun patchTaskById(name: String, description: String, executeAt: OffsetDateTime, id: Long): Task? {
        val taskToPatch = repository.getById(id) ?: error("Could not find task by id: '$id'")

        taskToPatch.name = name
        taskToPatch.description = description
        taskToPatch.executeAt = executeAt

        return repository.save(taskToPatch)
    }
}
