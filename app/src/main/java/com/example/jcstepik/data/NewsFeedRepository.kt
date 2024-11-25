package com.example.jcstepik.data

import android.app.Application
import androidx.compose.runtime.toMutableStateList
import com.example.jcstepik.data.mapper.NewsFeedMapper
import com.example.jcstepik.data.network.ApiFactory
import com.example.jcstepik.domain.FeedPost
import com.example.jcstepik.domain.PostComment
import com.example.jcstepik.domain.StatisticItem
import com.example.jcstepik.domain.StatisticType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token
        get() = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

//    private val coroutineScope = CoroutineScope(Dispatchers.Default)
//
//    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
//    private val
//

    private val _feedPost = mutableListOf<FeedPost>()
    val feedPost: List<FeedPost>
        get() = _feedPost.toList()

    private var nextFrom: String? = null

    suspend fun loadRecommendations(): List<FeedPost> {
       val startFrom = nextFrom

        if (startFrom == null && feedPost.isNotEmpty()) return feedPost

        val response = if (startFrom == null) {
            apiService.loadRecommendations(getAccessToken())
        } else {
            apiService.loadRecommendations(getAccessToken(), startFrom)
        }

        //val response = apiService.loadRecommendations(getAccessToken())
        nextFrom = response.newsFeedContent.nextFrom
        val posts = mapper.mapResponseToPost(response)
        _feedPost.addAll(posts)
        return feedPost

    }



    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

    suspend fun deletePost(feedPost: FeedPost) {
        apiService.ignorePost(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPost.remove(feedPost)
    }

    suspend fun getComments(feedPost: FeedPost): List<PostComment> {
        val comments = apiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        return mapper.mapResponseToComments(comments)
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }

        val newLikeCount = response.likes.count
        val newsStatistics = feedPost.statistics.toMutableStateList().apply {
            removeIf{ it.type == StatisticType.LIKE }
            add(StatisticItem( type  = StatisticType.LIKE, newLikeCount ))
        }
        val newPost = feedPost.copy(statistics = newsStatistics, isLiked = !feedPost.isLiked)
        val postIndex = _feedPost.indexOf(feedPost)
        _feedPost[postIndex] = newPost
    }
}