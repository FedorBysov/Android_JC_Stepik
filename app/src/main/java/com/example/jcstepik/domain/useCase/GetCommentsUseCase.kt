package com.example.jcstepik.domain.useCase

import com.example.jcstepik.domain.NewsFeedRepository
import com.example.jcstepik.domain.entity.FeedPost
import com.example.jcstepik.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCommentsUseCase  @Inject constructor(
    private val repository: NewsFeedRepository
){

    operator fun invoke(feedPost: FeedPost) : StateFlow<List<PostComment>> {
       return repository.getComments(feedPost)
    }


}