package org.example.service

import org.example.model.Task
import java.util.*

interface TaskService {

    fun save(task: Task): Task
    fun deleteById(id: Long): Boolean
    fun getTaskById(id: Long): Task?
}
