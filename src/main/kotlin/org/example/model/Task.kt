package org.example.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    // @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JoinColumn(name = "created_by")
    val createdBy: User,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val executeAt: OffsetDateTime? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    // @JoinColumn(name = "appointed_by", referencedColumnName = "id")
    @JoinColumn(name = "appointed_by")
    val appointedBy: User? = null,
    @OneToMany(mappedBy = "correspondingTask", fetch = FetchType.LAZY, orphanRemoval = true)
    val commentaries: List<Comment> = emptyList(),
    val tags: List<Tag> = emptyList(),
)

// enum class Tags {
//
//    COLLECTIVE, INDIVIDUAL, COMPULSORY, DELAYED, GUEST
// }
