package com.example.jcstepik.presentation.main

sealed class AuthState {

    object Initial : AuthState()

    object Authorized : AuthState()

    object NonAuthorized : AuthState()

}