package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    @OneToMany(mappedBy = "appointedBy", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var appointedTasks: List<Task>,
    @OneToMany(mappedBy = "createdBy", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var createdTasks: List<Task>,
)
