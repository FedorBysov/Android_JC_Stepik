package com.example.jcstepik.domain.useCase

import com.example.jcstepik.domain.NewsFeedRepository
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor(
    private val repository: NewsFeedRepository
){

    suspend operator fun invoke(){
        repository.checkAuthState()
    }

}