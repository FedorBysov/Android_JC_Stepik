package com.example.jcstepik.presentation.news

import com.example.jcstepik.domain.FeedPost

sealed class NewsFeedsScreenState {

    object Initial : NewsFeedsScreenState()

    data class Posts(val posts:List<FeedPost>, val nextDataIsLoading: Boolean = false) : NewsFeedsScreenState()

    object Loading : NewsFeedsScreenState()

}