package org.example.controller.task.dto

import org.example.model.Tag
import org.example.model.TaskState
import java.time.OffsetDateTime

data class PatchTaskRequest(
    val name: String,
    val description: String,
    val executeAt: OffsetDateTime,
    val taskState: TaskState,
    val tagsToAdd: List<Tag>,
)
