package org.example.controller

import org.example.service.CommentService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/commentaries")
class CommentController(private val commentService: CommentService)
