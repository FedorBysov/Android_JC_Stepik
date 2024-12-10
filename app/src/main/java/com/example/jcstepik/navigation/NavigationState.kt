package com.example.jcstepik.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jcstepik.domain.entity.FeedPost

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route:String){
        navHostController.navigate(route){
            popUpTo(navHostController.graph.startDestinationId){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToComment(feedPost: FeedPost){
        navHostController.navigate(Screen.Comments.getRouteWithArgs(feedPost))  // comments/15

    }

}
@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
) :NavigationState {
    return remember { NavigationState(navHostController) }
}