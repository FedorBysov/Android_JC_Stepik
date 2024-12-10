package com.example.jcstepik.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcstepik.data.NewsFeedRepositoryImpl
import com.example.jcstepik.domain.useCase.CheckAuthStateUseCase
import com.example.jcstepik.domain.useCase.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAuthStateFlowUseCase: GetAuthStateFlowUseCase,
    private val checkAuthStateUseCase: CheckAuthStateUseCase,
) : ViewModel() {

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }
    }

}