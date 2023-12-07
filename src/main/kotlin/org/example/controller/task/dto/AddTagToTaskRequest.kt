package org.example.controller.task.dto

import org.example.model.Tag

data class AddTagToTaskRequest(
    val tagsToAdd: List<Tag>,
)
