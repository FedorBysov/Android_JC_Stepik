package com.example.jcstepik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.jcstepik.domain.FeedPost


@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenUI: @Composable () -> Unit,
    favouriteScreenUI: @Composable () -> Unit,
    profileScreenUI: @Composable () -> Unit,
    commentsScreenContentUI: @Composable (FeedPost) -> Unit

) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
//        composable(Screen.NewsFeed.route) {
//            homeScreenUI()
//        }

        homeScreenNavGraph(
            newsFeedScreenContent = newsFeedScreenUI,
            commentScreenContent = commentsScreenContentUI
        )

        composable(Screen.FavoriteFeed.route) {
            favouriteScreenUI()
        }
        composable(Screen.ProfileFeed.route) {
            profileScreenUI()
        }
    }
}