package com.example.jcstepik.domain.useCase

import com.example.jcstepik.domain.NewsFeedRepository
import com.example.jcstepik.domain.entity.FeedPost
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRecommendationsUseCase  @Inject constructor(
    private val repository: NewsFeedRepository
){

    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendationsList()
    }

}