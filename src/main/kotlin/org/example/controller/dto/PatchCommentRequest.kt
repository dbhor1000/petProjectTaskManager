package org.example.controller.dto

import jakarta.persistence.*
import org.example.model.Task
import org.example.model.User

data class PatchCommentRequest (

    val commentText: String

)