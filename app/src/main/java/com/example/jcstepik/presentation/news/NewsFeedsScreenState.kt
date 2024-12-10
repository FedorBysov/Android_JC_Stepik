package com.example.jcstepik.presentation.news

import com.example.jcstepik.domain.entity.FeedPost

sealed class NewsFeedsScreenState {

    object Initial : NewsFeedsScreenState()

    data class Posts(val posts:List<FeedPost>, val nextDataIsLoading: Boolean = false) : NewsFeedsScreenState()

    object Loading : NewsFeedsScreenState()

}