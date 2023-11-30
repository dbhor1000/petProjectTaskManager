package controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.CommentariesService

@RestController
@RequestMapping("/commentaries")
class CommentariesController(private val commentariesService: CommentariesService) {
}