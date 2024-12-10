package com.example.jcstepik.domain

import com.example.jcstepik.domain.entity.FeedPost
import com.example.jcstepik.domain.entity.PostComment
import com.example.jcstepik.domain.entity.AuthState
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendationsList(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun deletePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}