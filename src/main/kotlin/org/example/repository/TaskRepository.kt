package org.example.repository

import org.example.model.Task
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TaskRepository : CrudRepository<Task, Long> {

    @Modifying
    @Query("delete from Task b where b.id=:id")
    override fun deleteById(id: Long)

    override fun existsById(id: Long): Boolean
}
