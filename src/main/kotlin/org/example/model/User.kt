package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val email: String,
    //@OneToMany(mappedBy = "correspondingTask", fetch = FetchType.LAZY)
    //var tasks: List<Task>?

)