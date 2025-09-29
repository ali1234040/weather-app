package com.example.waetherapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.waetherapp.CompuseExtensions.CustomText
import com.example.waetherapp.CompuseExtensions.Height
import com.example.waetherapp.R
import com.example.waetherapp.data.remote.PastWeatherModel
import com.example.waetherapp.viewModel.DateViewModel
import com.example.waetherapp.viewModel.WeatherViewModel

//TODO CONNECT TO VPN
// recrate() برای رفرش کردن صفحه
@Composable
fun WeatherScreen(
    weatherVM: WeatherViewModel = hiltViewModel<WeatherViewModel>(),
    dateVM: DateViewModel = hiltViewModel<DateViewModel>(),
    navController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue))
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        var weatherTemp by remember { mutableStateOf("") }
        var weatherCondition by remember { mutableStateOf("") }

        weatherVM.currentWeatherData { weatherData ->
            weatherTemp = weatherData.current.tempC.toString()
            weatherCondition = weatherData.current.condition.text
        }

        val listWeekDays = dateVM.strDay()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Height(200)


            val pastWeatherData by weatherVM.pastWeatherData.collectAsState()

            if (pastWeatherData.isEmpty()) {
                Text("Loading...")
            } else {
                Column {
                    TodayItem(weekDays = listWeekDays[0], weatherCondition, weatherTemp)
                    PastItem(weekDays = listWeekDays[1], weatherData = pastWeatherData[0])
                    PastItem(weekDays = listWeekDays[2], weatherData = pastWeatherData[1])
                    PastItem(weekDays = listWeekDays[3], weatherData = pastWeatherData[2])
                    PastItem(weekDays = listWeekDays[4], weatherData = pastWeatherData[3])
                }
            }
        }
    }
}

@Composable
fun TodayItem(weekDays: String, daysCondition: String, tempC: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.teal_200))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            CustomText(weekDays)
            Spacer(modifier = Modifier.width(20.dp))
            CustomText(daysCondition)
            Spacer(modifier = Modifier.width(20.dp))
            CustomText(tempC)

        }
    }
}

@Composable
fun PastItem(weekDays: String, weatherData: PastWeatherModel?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.teal_200))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            if (weatherData != null) {
                CustomText(weekDays)
                Spacer(modifier = Modifier.width(20.dp))
                CustomText(weatherData.forecast.forecastday[0].day.condition.text)
                Spacer(modifier = Modifier.width(20.dp))
                CustomText(weatherData.forecast.forecastday[0].day.avgTempC.toString())
            }
        }
    }
}
