package com.example.waetherapp.repository

import android.util.Log
import com.example.waetherapp.Base.WeekDayList
import com.example.waetherapp.data.remote.ApiService
import com.example.waetherapp.data.remote.CurrentWeatherModel
import com.example.waetherapp.data.remote.PastWeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateRepository @Inject constructor(
    private val date: WeekDayList,
    private val apiService: ApiService
) {

    fun getToDay() = date.listWeekDays

    private fun getCurrentGregorianDateAsString(): String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return now.format(formatter)
    }

    private fun getPastDateAsString(daysAgo: Int): String {
        val pastDate = LocalDateTime.now().minusDays(daysAgo.toLong())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return pastDate.format(formatter)
    }

    suspend fun getCurrentWeatherData(): CurrentWeatherModel {
        return apiService.getCurrentWeather()
    }

    suspend fun getPastWeatherData(daysAgo: Int): PastWeatherModel {
        return try {
            val pastDate = getPastDateAsString(daysAgo)
            Log.i("ERROR_TAG", "items 1")
            apiService.getPastWeather(date = pastDate)
        } catch (e: Throwable) {
            Log.i("ERROR_TAG", "items 2")
            PastWeatherModel.EMPTY
        }
    }
}