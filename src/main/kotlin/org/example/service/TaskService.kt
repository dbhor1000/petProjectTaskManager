package org.example.service

import org.example.model.Task

interface TaskService {

    fun save(task: Task): Task
}
