package org.example.controller.dto.common

import jakarta.persistence.*
import org.example.controller.dto.common.TaskDto.Companion.toDto
import org.example.model.Comment
import org.example.model.Task
import org.example.model.User
import java.time.OffsetDateTime

data class UserDto(

    val id: Long? = null,
    val name: String,
    val email: String,
    var appointedTasks: List<TaskDto>,
    var createdTasks: List<TaskDto>,

) {
    companion object {

        fun User.toDto() = UserDto(

            id = id,
            name = name,
            email = email,
            appointedTasks = appointedTasks.map { it.toDto() },
            createdTasks = appointedTasks.map { it.toDto() }

        )
    }
}

data class UserDtoBasic(

    val id: Long? = null,
    val name: String,
    val email: String,

) {
    companion object {

        fun User.toDtoBasic() = UserDtoBasic(

            id = id,
            name = name,
            email = email
        )
    }
}

data class CommentDto(
    val id: Long? = null,
    var commentText: String,
) {
    companion object {

        fun Comment.toDto() = CommentDto(

            id = id,
            commentText = commentText

        )
    }
}

data class TaskDto(
    val id: Long? = null,
    var name: String,
    var description: String,
    val createdBy: User,
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
            createdBy = createdBy,
            createdAt = createdAt,
            executeAt = executeAt,
            appointedBy = appointedBy?.id,
            comments = commentaries.map { CommentDto(it.id, it.commentText) },
        )
    }
}
