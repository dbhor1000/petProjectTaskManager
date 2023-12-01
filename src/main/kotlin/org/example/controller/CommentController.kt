package org.example.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.example.service.CommentService

@RestController
@RequestMapping("/commentaries")
class CommentController(private val commentService: CommentService) {

}