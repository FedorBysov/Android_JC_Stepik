package com.example.jcstepik.domain

import com.example.jcstepik.R

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicationDate: String

)