package com.example.waetherapp.navigation


sealed class ScreensName(val route: String) {
    object Splash : ScreensName("splash")
    object Weather : ScreensName("weather")
}
