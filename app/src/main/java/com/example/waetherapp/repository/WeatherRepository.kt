package com.example.waetherapp.repository

import android.util.Log
import com.example.waetherapp.data.remote.PastWeatherModel
import javax.inject.Inject

class WeatherRepository @Inject constructor( private val dateRep: DateRepository) {

    suspend fun getPastData():List<PastWeatherModel> {
        val updatedList = mutableListOf<PastWeatherModel>()
        return try {
            repeat(4) { daysAgo ->
                val weather = dateRep.getPastWeatherData(daysAgo + 1)
                updatedList.add(weather)
                Log.i("ERROR_TAG", "$daysAgo items")
            }
            updatedList
        } catch (e: Throwable) {
            Log.e("ERROR_TAG", "Error loading past weather")
             emptyList()
        }
    }
}



