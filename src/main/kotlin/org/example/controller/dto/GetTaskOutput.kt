package org.example.controller.dto

import jakarta.persistence.*
import org.example.model.Tag
import org.example.model.User
import java.time.OffsetDateTime

data class GetTaskOutput(

    val id: Long? = null,
    var name: String,
    var description: String,
    val createdBy: User,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    var executeAt: OffsetDateTime? = null,
    val appointedBy: User? = null,
    var tags: List<Tag> = emptyList()

)
