package org.example.controller.dto

data class CreateCommentRequest(

    val commentText: String,
    val author: Long,
    val correspondingTask: Long

)
