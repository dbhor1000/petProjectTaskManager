package org.example.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var description: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "created_by")
    val createdBy: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "appointed_by")
    val appointedBy: User? = null,
    @OneToMany(mappedBy = "correspondingTask", fetch = FetchType.LAZY, orphanRemoval = true)
    val commentaries: List<Comment> = emptyList(),
    @Enumerated(EnumType.STRING)
    var taskState: TaskState = TaskState.CREATED,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    var executeAt: OffsetDateTime? = null,
    var completedAt: OffsetDateTime? = null,
)

enum class TaskState { CREATED, IN_DEVELOPMENT, COMPLETED }

// enum class Tags {
//
//    COLLECTIVE, INDIVIDUAL, COMPULSORY, DELAYED, GUEST
// }
