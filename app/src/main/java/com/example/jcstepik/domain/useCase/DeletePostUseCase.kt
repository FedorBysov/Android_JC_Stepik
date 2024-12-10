package com.example.jcstepik.domain.useCase

import com.example.jcstepik.domain.NewsFeedRepository
import com.example.jcstepik.domain.entity.FeedPost
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val repository: NewsFeedRepository
){

    suspend operator fun invoke(feedPost: FeedPost){
        repository.deletePost(feedPost)
    }

}