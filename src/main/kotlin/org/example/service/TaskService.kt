package org.example.service

import org.example.controller.dto.AddTagToTask
import org.example.controller.dto.CreateTaskRequest
import org.example.controller.dto.GetTaskOutput
import org.example.controller.dto.PatchTaskRequest
import org.example.model.Task
import java.util.*

interface TaskService {

    fun save(task: Task): Task
    fun deleteById(id: Long): Boolean
    // fun getTaskById(id: Long): Task?
    fun getTaskById(id: Long): GetTaskOutput?
    fun addTask(request: CreateTaskRequest): Boolean
    fun patchTaskById(request: PatchTaskRequest, id: Long): Boolean
    fun addTagToTaskById(request: AddTagToTask, id: Long): Boolean
}
