package com.example.jcstepik.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jcstepik.navigation.AppNavGraph
import com.example.jcstepik.navigation.rememberNavigationState
import com.example.jcstepik.presentation.ViewModelFactory
import com.example.jcstepik.presentation.comments.CommentScreen
import com.example.jcstepik.presentation.news.NewsFeedScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {


    val navigationState = rememberNavigationState()



    Scaffold(

        bottomBar = {

            NavigationBar {

                Log.d("COMPOSE_TEST", "BottomNavigation")

                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()


                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )

                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false


                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            selectedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                            disabledIconColor = MaterialTheme.colorScheme.onSecondary,
                            disabledTextColor = MaterialTheme.colorScheme.onSecondary

                        ),

                        )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenUI = {

                NewsFeedScreen(
                    viewModelFactory = viewModelFactory,
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        navigationState.navigateToComment(it)
                    }
                )
            },
            commentsScreenContentUI = { feedPost ->
                CommentScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            },


            favouriteScreenUI = { Text("Favourite") },
            profileScreenUI = { Text("Profile") },

            )


    }
}


//@Composable
//private fun TextCounter(name: String) {
//    var count by rememberSaveable {
//        mutableStateOf(0)
//    }
//
//    Text(
//        modifier = Modifier.clickable { count++ },
//        text = "$name Count: $count",
//        color = Color.Black
//    )
//}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}