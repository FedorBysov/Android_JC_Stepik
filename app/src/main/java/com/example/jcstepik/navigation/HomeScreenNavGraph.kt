package com.example.jcstepik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.jcstepik.domain.FeedPost


fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent : @Composable () -> Unit,
    commentScreenContent : @Composable (FeedPost) -> Unit
){

    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ){
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(
            route=Screen.Comments.route,
            arguments = listOf(
                navArgument( Screen.KEY_FEED_POST){
                    type = FeedPost.NavigationType
                }
            )
            ) {
            //comments/{feed_post_id}
            val feedPost = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                ?: throw RuntimeException("Args is null")
            commentScreenContent(feedPost)
        }
    }

}