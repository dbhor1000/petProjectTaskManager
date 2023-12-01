package org.example.controller.dto

data class CreateTaskRequest(
    val name: String,
    val description: String,
    val userId: Long,
)
