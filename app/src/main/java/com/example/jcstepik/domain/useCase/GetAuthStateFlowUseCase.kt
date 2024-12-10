package com.example.jcstepik.domain.useCase

import com.example.jcstepik.domain.NewsFeedRepository
import com.example.jcstepik.domain.entity.AuthState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateFlowUseCase @Inject constructor(
    private val repository: NewsFeedRepository
){

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }

}