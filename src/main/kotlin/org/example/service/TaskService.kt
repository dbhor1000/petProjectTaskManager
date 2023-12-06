package org.example.service

import org.example.controller.dto.AddTagToTaskRequest
import org.example.controller.dto.CreateTaskRequest
import org.example.controller.dto.GetTaskResponse
import org.example.controller.dto.PatchTaskRequest
import org.example.model.Task

interface TaskService {

    fun save(task: Task): Task
    fun deleteById(id: Long): Boolean
    // fun getTaskById(id: Long): Task?
    fun getTaskById(id: Long): GetTaskResponse?
    fun addTask(request: CreateTaskRequest): Boolean
    fun patchTaskById(request: PatchTaskRequest, id: Long): Boolean
    fun addTagToTaskById(request: AddTagToTaskRequest, id: Long): Boolean
}
