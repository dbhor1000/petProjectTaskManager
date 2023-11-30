package repository

import model.Comment
import org.springframework.data.repository.CrudRepository

interface CommentariesRepository : CrudRepository<Comment, Int> {
}