package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "commentaries")
data class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var commentText: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    val author: User,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TASK_ID", nullable = false)
    val correspondingTask: Task
)
