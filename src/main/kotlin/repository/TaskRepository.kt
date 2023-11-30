package repository

import model.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Int> {
}