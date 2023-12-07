package org.example.repository

import org.example.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    @Query(
        """
            select * from users where id = :id
        """,
        nativeQuery = true,
    )
    fun getById(id: Long): User?
}
