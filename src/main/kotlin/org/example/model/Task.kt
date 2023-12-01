package org.example.model;

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "tasks")
data class Task(


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    //val createdByUser: User,
    val createdByUser: Long,
    val createdAt: OffsetDateTime,
    val executeAt: OffsetDateTime? = null,
    //@ManyToOne
    //@JoinColumn(name = "scenario_aggregate_id", referencedColumnName = "id")
    //val usersInvolved: List<User>,
    val usersInvolved: Long,
    @OneToMany(mappedBy = "correspondingTask", fetch = FetchType.LAZY, orphanRemoval = true)
    val commentaries: List<Comment>,
//    val tags: List<Tags>

)

//enum class Tags {
//
//    COLLECTIVE, INDIVIDUAL, COMPULSORY, DELAYED, GUEST
//
//}
