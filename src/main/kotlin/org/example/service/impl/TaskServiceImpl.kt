package org.example.service.impl

import org.example.model.Task
import org.example.repository.TaskRepository
import org.example.service.TaskService
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val repository: TaskRepository) : TaskService {

    override fun save(task: Task): Task = repository.save(task)

    override fun deleteById(id: Long): Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }
        return false
    }

    override fun getTaskById(id: Long): Task {

        val taskFound = repository.findByIdOrNull(id)
        return taskFound
    }
}
