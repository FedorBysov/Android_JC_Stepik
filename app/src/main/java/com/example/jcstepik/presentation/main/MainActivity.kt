package com.example.jcstepik.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jcstepik.domain.entity.AuthState
import com.example.jcstepik.presentation.NewsFeedApplications
import com.example.jcstepik.presentation.ViewModelFactory
import com.example.jcstepik.ui.theme.JCStepikTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import javax.inject.Inject

class MainActivity : ComponentActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as NewsFeedApplications).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            JCStepikTheme(dynamicColor = false) {
                val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
                val authState = viewModel.authState.collectAsState(AuthState.Initial)
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    viewModel.performAuthResult()
                }

                when (authState.value) {
                    is AuthState.Authorized -> {
                        MainScreen(viewModelFactory)
                    }

                    is AuthState.NonAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.WALL, VKScope.FRIENDS))
                        }
                    }

                    else -> {}
                }

            }
        }
    }
}



