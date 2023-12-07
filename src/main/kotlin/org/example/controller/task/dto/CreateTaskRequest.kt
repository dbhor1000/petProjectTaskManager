package org.example.controller.task.dto

import java.time.OffsetDateTime

data class CreateTaskRequest(
    val name: String,
    val description: String,
    val executeAt: OffsetDateTime,
    val userId: Long,
)
