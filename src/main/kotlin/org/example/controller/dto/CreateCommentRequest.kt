package org.example.controller.dto

import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.example.model.Task
import org.example.model.User

data class CreateCommentRequest (

    val commentText: String,
    val author: Long,
    val correspondingTask: Long

)