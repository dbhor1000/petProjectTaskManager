package org.example.service

import org.example.controller.dto.CreateCommentRequest
import org.example.controller.dto.GetCommentOutput
import org.example.controller.dto.PatchCommentRequest
import org.example.model.Comment

interface CommentService {

    fun save(comment: Comment): Comment
    fun deleteById(id: Long): Boolean
    fun getCommentById(id: Long): GetCommentOutput?
    fun addComment(request: CreateCommentRequest): Boolean
    fun patchCommentById(request: PatchCommentRequest, id: Long): Boolean
}
