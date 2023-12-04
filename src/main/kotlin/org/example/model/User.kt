package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    @OneToMany(mappedBy = "appointedBy", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var appointedTasks: List<Task>,
    @OneToMany(mappedBy = "createdBy", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var createdTasks: List<Task>,
)
