package org.example.service

import org.example.controller.task.dto.PatchTaskRequest
import org.example.model.Task
import java.time.OffsetDateTime

interface TaskService {
    fun save(task: Task): Task
    fun deleteById(id: Long): Boolean
    fun getTaskById(id: Long): Task
    fun addTask(name: String, description: String, userId: Long, executeAt: OffsetDateTime): Task
    fun patchTaskById(request: PatchTaskRequest, id: Long): Task
}
