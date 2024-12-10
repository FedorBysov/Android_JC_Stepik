package com.example.jcstepik.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcstepik.data.NewsFeedRepositoryImpl
import com.example.jcstepik.domain.entity.FeedPost
import com.example.jcstepik.domain.useCase.ChangeLikeStatusUseCase
import com.example.jcstepik.domain.useCase.DeletePostUseCase
import com.example.jcstepik.domain.useCase.GetRecommendationsUseCase
import com.example.jcstepik.domain.useCase.LoadNextDataUseCase
import com.example.jcstepik.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val deletePostUseCase: DeletePostUseCase,
) : ViewModel() {


    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("NewsFeedViewModel", "Exception caught by exception handler")
    }


    private val recommendationsFlow = getRecommendationsUseCase()

    private val loadNextDataFlow = MutableSharedFlow<NewsFeedsScreenState>()

    val screenState = recommendationsFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedsScreenState.Posts(posts = it) as NewsFeedsScreenState }
        .onStart { emit(NewsFeedsScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataFlow.emit(
                NewsFeedsScreenState.Posts(
                    posts = recommendationsFlow.value,
                    nextDataIsLoading = true
                )
            )
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost)
        }
    }
}