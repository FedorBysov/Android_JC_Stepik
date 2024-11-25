package com.example.jcstepik.presentation.comments

import com.example.jcstepik.domain.FeedPost
import com.example.jcstepik.domain.PostComment

sealed class CommentsScreenState {


    object Initialize: CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentsScreenState()
}