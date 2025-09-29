package com.example.waetherapp.data.remote

import com.google.gson.annotations.SerializedName

data class CurrentWeatherModel(
    val current : Current,
)

data class Current(
    @SerializedName("temp_c")
    val tempC : Double ,
    @SerializedName("is_day")
    val isDay : Int,
    val condition : Condition
)

data class Condition(
    val text : String
) {
    companion object {
        val EMPTY = Condition(text = "N/A")
    }
}

/////////////////

data class PastWeatherModel(
    val forecast : Forecast
){
    companion object {
        val EMPTY = PastWeatherModel(
            forecast = Forecast(
                forecastday = listOf(
                    Forecastday(
                        day = Day(
                            avgTempC = 0.0,
                            condition = Condition.EMPTY
                        ),
                        hour = listOf(
                            Hour(isDay = 0)
                        )
                    )
                )
            )
        )
    }
}

data class Forecast(
    val forecastday : List<Forecastday>
)

data class Forecastday (
    val day : Day,
    val hour: List<Hour>
)

data class Day(
    @SerializedName("avgtemp_c")
    val avgTempC : Double,
    val condition: Condition
)

data class Hour(
    @SerializedName("is_day")
    val isDay : Int,
)


