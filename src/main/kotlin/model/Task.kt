package model;

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "tasks")
data class Task(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val createdByUser: User,
    val createdAt: OffsetDateTime,
    val executeAt: OffsetDateTime? = null,
    @OneToMany(mappedBy = "tasks", fetch = FetchType.LAZY, orphanRemoval = true)
    val usersInvolved: List<User>,
    @OneToMany(mappedBy = "correspondingTask", fetch = FetchType.LAZY, orphanRemoval = true)
    val commentaries: List<Comment>,
    val tags: List<Tags>

)

enum class Tags {

    COLLECTIVE, INDIVIDUAL, COMPULSORY, DELAYED, GUEST

}
