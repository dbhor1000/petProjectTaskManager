package org.example.controller.dto

import org.example.model.Task
import org.example.model.User

data class GetCommentResponse(
    var commentText: String,
    val author: User,
    val correspondingTask: Task,
)
