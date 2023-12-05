package org.example.controller.dto

import jakarta.persistence.*
import org.example.model.Task
import org.example.model.User

data class GetCommentOutput(

    var commentText: String,
    val author: User,
    val correspondingTask: Task

)
