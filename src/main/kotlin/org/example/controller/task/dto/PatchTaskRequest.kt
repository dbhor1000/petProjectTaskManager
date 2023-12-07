package org.example.controller.task.dto

import java.time.OffsetDateTime

data class PatchTaskRequest(
    val name: String,
    val description: String,
    val executeAt: OffsetDateTime,
    // val taskState: TaskState,
    // val tagsToAdd: List<Tag>,
)
