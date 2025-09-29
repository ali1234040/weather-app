package com.example.waetherapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.waetherapp.navigation.ScreensName
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(Unit) {
        delay(2.seconds)
        navController.navigate( ScreensName.Weather.route) {
            popUpTo(ScreensName.Splash.route) { inclusive = true }
        }
    }
Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)
){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Please make sure you are connected to a VPN")

        Text("لطفا از اتصال خود به وی پی ان اطمینان حاصل کنید")
    }
}

}