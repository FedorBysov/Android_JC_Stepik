package com.example.jcstepik.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.jcstepik.data.NewsFeedRepositoryImpl
import com.example.jcstepik.domain.entity.FeedPost
import com.example.jcstepik.domain.useCase.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {



    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}