package com.example.dolegal.presentation.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dolegal.presentation.screens.home_screen.MainScreen
import com.example.dolegal.presentation.screens.home_screen.StoryPlayerScreen
import com.example.dolegal.presentation.screens.home_screen.StoryScreen
import com.example.dolegal.presentation.screens.login_signup.LoginSignup
import com.example.dolegal.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Navigation(
    lifecycleOwner: LifecycleOwner,
    context: Context,
    innerpadding: PaddingValues,
    currentUser: Boolean
) {
    val userViewModel = hiltViewModel<HomeViewModel>()
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = if (!currentUser) NavScreens.LoginScreen.route else NavScreens.HomeScreen.route
    ) {

        composable(NavScreens.LoginScreen.route) {
            LoginSignup(
                innerPadding = innerpadding,
                lifecycleOwner = lifecycleOwner,
                context = context,
                vModel = userViewModel,
                onLoginClick = {
                    navigationController.navigate(NavScreens.HomeScreen.route);Log.d(
                    "Vandan_LOGIN",
                    it.toString()
                )
                    GlobalScope.launch {
                        userViewModel.getCurrentUser()
                    }
                }
            )
        }

        composable(NavScreens.HomeScreen.route) {

            MainScreen(
                viewModel = userViewModel,
                lifecycleOwner = lifecycleOwner,
                innerPadding = innerpadding,
                onLogoutClick = { navigationController.navigate(NavScreens.LoginScreen.route) },
                onCategoryClick = {category->
                    navigationController.navigate(NavScreens.StoryScreen.route+"/${category}")
                    userViewModel.getAllStories(category)
                }
            )


        }

        composable(NavScreens.StoryScreen.route+"/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )) {
            val mcategory = it.arguments?.getString("category")
            StoryScreen(
                category = mcategory,
                lifecycleOwner = lifecycleOwner,
                viewModel = userViewModel,
                context = context,
                innerPadding = innerpadding,
                onStoryClick = {navigationController.navigate(NavScreens.StoryPLayerScreen.route+"/${it}")}
            )

        }

        composable(NavScreens.StoryPLayerScreen.route+ "/{storyId}", arguments = listOf(
            navArgument("storyId"){
                type = NavType.StringType
            }
        )) {
            val storyId = it.arguments?.getString("storyId")
            StoryPlayerScreen(storyID = storyId, context = context, lifecycleOwner = lifecycleOwner)
        }


    }

}