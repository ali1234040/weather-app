package com.example.waetherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.waetherapp.ui.screen.SplashScreen
import com.example.waetherapp.ui.screen.WeatherScreen

@Composable
fun SetupNavigation(
    navController: NavHostController,
) {

    NavHost(
        navController = navController ,
        startDestination = ScreensName.Splash.route
    ) {
        composable(
            route = ScreensName.Splash.route
        ){ SplashScreen(navController = navController) }

        composable(
            route = ScreensName.Weather.route
        ){ WeatherScreen(navController = navController) }
    }
}

