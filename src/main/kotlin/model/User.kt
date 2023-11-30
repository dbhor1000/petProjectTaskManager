package model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    @ManyToMany(mappedBy = "correspondingTask", fetch = FetchType.LAZY)
    var tasks: List<Task>?

)