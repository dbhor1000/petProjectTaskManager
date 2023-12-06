package org.example.controller.dto.common

import org.example.model.Task
import java.time.OffsetDateTime

data class CommentDto(
    val id: Long? = null,
    var commentText: String,
)

data class TaskDto(
    val id: Long? = null,
    var name: String,
    var description: String,
    val createdBy: Long,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    var executeAt: OffsetDateTime? = null,
    val comments: List<CommentDto>,
    val appointedBy: Long? = null,
) {

    companion object {

        // TODO: Сделать по аналогии с этим comments, user
        fun Task.toDto() = TaskDto(
            id = id,
            name = name,
            description = description,
            createdBy = createdBy.id!!,
            createdAt = createdAt,
            executeAt = executeAt,
            appointedBy = appointedBy?.id,
            comments = commentaries.map { CommentDto(it.id, it.commentText) },
        )
    }
}
