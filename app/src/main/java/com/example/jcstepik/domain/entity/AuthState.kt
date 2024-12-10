package com.example.jcstepik.domain.entity

sealed class AuthState {

    object Initial : AuthState()

    object Authorized : AuthState()

    object NonAuthorized : AuthState()


}