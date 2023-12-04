package org.example.controller.dto

import java.time.OffsetDateTime

data class PatchTaskRequest(

    val name: String,
    val description: String,
    val executeAt: OffsetDateTime

)
