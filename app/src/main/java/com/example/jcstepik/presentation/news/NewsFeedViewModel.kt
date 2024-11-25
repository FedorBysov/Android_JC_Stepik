package com.example.jcstepik.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jcstepik.data.NewsFeedRepository
import com.example.jcstepik.domain.FeedPost
import com.example.jcstepik.domain.StatisticItem
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {



    private val initialState = NewsFeedsScreenState.Initial

    private val _screenState = MutableLiveData<NewsFeedsScreenState>(initialState)
    val screenState: LiveData<NewsFeedsScreenState> = _screenState

    private val repository = NewsFeedRepository(application)


    init {
        _screenState.value = NewsFeedsScreenState.Loading
        loadRecommendations()
    }


    private fun loadRecommendations() {
        viewModelScope.launch {
            val feedPosts = repository.loadRecommendations()
            _screenState.value = NewsFeedsScreenState.Posts(posts = feedPosts)
        }
    }


    fun loadNextRecommendations(){
        _screenState.value = NewsFeedsScreenState.Posts(
            posts = repository.feedPost,
            nextDataIsLoading = true
        )
        loadRecommendations()
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = NewsFeedsScreenState.Posts(posts = repository.feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.deletePost(feedPost)
            _screenState.value = NewsFeedsScreenState.Posts(posts = repository.feedPost)
        }
    }

}